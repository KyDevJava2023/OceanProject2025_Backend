package com.ocean.backend.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ocean.backend.entity.Product;
import com.ocean.backend.repository.CategoryRepository;
import com.ocean.backend.repository.ProductRepository;
import com.ocean.backend.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/san-pham")
public class ProductController {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    @GetMapping
    public String productList(
            @RequestParam(name = "cate", required = false) String cate,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {
        int size = 9; // 9 sp / trang

        // chặn page âm
        if (cate != null && cate.trim().isEmpty()) {
            cate = null;
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productRepo.findForList(cate, Status.ACTIVE, pageable);

        // list cate cho sidebar (nếu có)
        // var categories = categoryRepo.findByTypeAndStatusOrderByNameAsc("PRODUCT",
        // Status.ACTIVE);

        model.addAttribute("products", products);
        // model.addAttribute("categories", categories);
        model.addAttribute("currentCate", cate);
        model.addAttribute("content", "site/product/productList");
        return "site/layout/base";
    }
}
