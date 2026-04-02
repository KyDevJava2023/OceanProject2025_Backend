package com.ocean.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
        @GetMapping("/gioi-thieu")
        public String about(Model model) {
               
                model.addAttribute("content", "site/page/about");
                return "site/layout/base";
        }

        @GetMapping("/lien-he")
        public String contact(Model model) {
              
                model.addAttribute("content", "site/page/contact");
                return "site/layout/base";
        }
        @GetMapping("/dich-vu")
        public String service(Model model) {
              
                model.addAttribute("content", "site/page/service");
                return "site/layout/base";
        }
}