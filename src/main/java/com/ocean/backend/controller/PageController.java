package com.ocean.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ocean.backend.service.CategoryService;
@Controller
public class PageController {
        private final CategoryService categoryService;

        public PageController(CategoryService categoryService) {
                this.categoryService = categoryService;
        }


        @GetMapping("/gioi-thieu")
        public String about(Model model) {

                model.addAttribute("content", "site/page/about");
                return "site/layout/base";
        }

        @GetMapping("/lien-he")
        public String contact(Model model) {
                model.addAttribute("categories", categoryService.getCategoryTree());
               
                model.addAttribute("content", "site/page/contact");
                return "site/layout/base";
        }

        @GetMapping("/dich-vu")
        public String service(Model model) {

                model.addAttribute("content", "site/page/service");
                return "site/layout/base";
        }
}