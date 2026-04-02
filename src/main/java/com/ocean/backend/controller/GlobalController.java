package com.ocean.backend.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute
    public void addGlobalAttributes(HttpServletRequest request, Model model) {
        // URL path hiện tại: /, /san-pham, /san-pham/thung-carton...
        String currentPath = request.getRequestURI();

        model.addAttribute("currentPath", currentPath);
    }
}
