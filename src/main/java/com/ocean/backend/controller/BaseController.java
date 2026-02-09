package com.ocean.backend.controller;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;



public abstract class BaseController {

    protected void addSeoUrls(HttpServletRequest request, Model model) {
        String baseUrl = request.getScheme() + "://" + request.getServerName()
                + ((request.getServerPort() == 80 || request.getServerPort() == 443)
                ? "" : ":" + request.getServerPort());

        String currentUrl = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            currentUrl += "?" + request.getQueryString();
        }

        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("currentUrl", currentUrl);
    }
}