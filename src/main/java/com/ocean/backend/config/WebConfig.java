// package com.ocean.backend.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**") // Cho phép tất cả các path
//             .allowedOrigins("http://localhost:3000") // Cho phép frontend
//             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//             .allowedHeaders("*")
//             .allowCredentials(true); // Nếu bạn dùng cookie hoặc auth header
//     }
// }