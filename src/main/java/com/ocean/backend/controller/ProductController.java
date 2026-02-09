package com.ocean.backend.controller;

import java.math.BigDecimal;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ocean.backend.entity.enums.ProductSort;
import com.ocean.backend.entity.enums.Status;
import com.ocean.backend.repository.CategoryRepository;
import com.ocean.backend.repository.ProductRepository;
import com.ocean.backend.repository.ProductSpecs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/san-pham")
public class ProductController extends BaseController {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    @GetMapping
    public String productList(
            HttpServletRequest request,
            @RequestParam(name = "cate", required = false) String cate,
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {
        int size = 9;
        page = Math.max(page, 0);

        // normalize
        if (cate != null && cate.trim().isEmpty())
            cate = null;
        if (q != null && q.trim().isEmpty())
            q = null;
        if (sort == null || sort.trim().isEmpty())
            sort = "new";

        var sortEnum = ProductSort.from(sort);
        var pageable = PageRequest.of(page, size, sortEnum.sort());

        var spec = ProductSpecs.isActive()
                .and(ProductSpecs.notDeleted())
                .and(ProductSpecs.categorySlug(cate))
                .and(ProductSpecs.keyword(q))
                .and(ProductSpecs.maxPrice(maxPrice)); // ✅ APPLY PRICE

        var products = productRepo.findAll(spec, pageable);

        // sidebar
        var categoryCounts = categoryRepo.findProductCategoryCounts(Status.ACTIVE, Status.ACTIVE);
        var featuredProducts = productRepo
                .findTop3ByIsBestSellerTrueAndStatusAndDeletedAtIsNullOrderByUpdatedAtDesc(Status.ACTIVE);
        var maxPriceDb = productRepo.findMaxPrice(Status.ACTIVE);

        model.addAttribute("products", products);
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("maxPriceDb", maxPriceDb);

        // nếu bạn cần list categories riêng
        var categories = categoryRepo.findByTypeAndStatusOrderByNameAsc("PRODUCT", Status.ACTIVE);
        model.addAttribute("categories", categories);
        addSeoUrls(request, model);

        model.addAttribute("schemaPage", "productList");
        // giữ state UI
        model.addAttribute("currentCate", cate);
        model.addAttribute("q", q);
        model.addAttribute("sort", sort);
        model.addAttribute("maxPrice", maxPrice);

        model.addAttribute("content", "site/product/productList");
        return "site/layout/base";
    }

    @GetMapping("/{slug}")
    public String productDetail(@PathVariable String slug, HttpServletRequest request, Model model) {

        var product = productRepo.findBySlugAndStatusAndDeletedAtIsNull(slug, Status.ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var categoryCounts = categoryRepo.findProductCategoryCounts(Status.ACTIVE, Status.ACTIVE);

        var featuredProducts = productRepo
                .findTop3ByIsBestSellerTrueAndStatusAndDeletedAtIsNullOrderByUpdatedAtDesc(Status.ACTIVE);

        var relatedProducts = productRepo
                .findTop8ByCategory_IdAndIdNotAndStatusAndDeletedAtIsNullOrderByCreatedAtDesc(
                        product.getCategory().getId(), product.getId(), Status.ACTIVE);

        model.addAttribute("product", product);
        model.addAttribute("categoryCounts", categoryCounts);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("relatedProducts", relatedProducts);
        addSeoUrls(request, model);
        model.addAttribute("schemaPage", "productDetail");
        // sidebar state: search form submit về list
        model.addAttribute("q", null);
        model.addAttribute("currentCate", product.getCategory().getSlug());

        model.addAttribute("content", "site/product/productDetail");
        return "site/layout/base";
    }
}
