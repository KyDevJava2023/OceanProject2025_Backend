package com.ocean.backend.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAuthController {

    @GetMapping("/admin/login")
    public String login() {
        return "admin/auth/login";
    }

    @GetMapping("/admin")
    public String dashboard() {
        return "admin/index";
    }
}