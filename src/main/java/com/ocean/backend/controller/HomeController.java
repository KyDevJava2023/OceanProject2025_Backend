package com.ocean.backend.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ocean.backend.entity.Product;
import com.ocean.backend.entity.enums.Status;
import com.ocean.backend.repository.CategoryRepository;
import com.ocean.backend.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController extends BaseController {
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        // 1) lấy 5 category PRODUCT
        var topCats = categoryRepo.findTop5ByTypeAndStatusOrderByIdAsc("PRODUCT", Status.ACTIVE);
        var cartonProducts = productRepo
                .findTop12ByCategory_SlugAndCategory_TypeAndDeletedAtIsNullAndStatusOrderByIdDesc(
                        "thung-carton", "PRODUCT", Status.ACTIVE);
        var bestSellers = productRepo
                .findTop6ByIsBestSellerTrueAndStatusAndDeletedAtIsNullOrderByUpdatedAtDesc(
                        Status.ACTIVE);

        // 2) map categoryId -> list products
        Map<Long, List<Product>> productsByCat = new LinkedHashMap<>();
        for (var c : topCats) {
            var prods = productRepo
                    .findTop8ByCategory_IdAndDeletedAtIsNullAndStatusOrderByIdDesc(c.getId(), Status.ACTIVE);
            productsByCat.put(c.getId(), prods);
        }
        model.addAttribute("cartonProducts", cartonProducts);
        model.addAttribute("topCats", topCats);
        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("productsByCat", productsByCat);
        addSeoUrls(request, model);
        model.addAttribute("schemaPage", "home");
        model.addAttribute("content", "site/home");
        return "site/layout/base";
    }

}
