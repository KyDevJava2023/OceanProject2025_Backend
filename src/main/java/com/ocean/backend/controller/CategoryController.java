// package com.ocean.backend.controller;

// import com.ocean.backend.dto.CategoryTreeDto;
// import com.ocean.backend.service.CategoryService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/categories")
// public class CategoryController {

//     private final CategoryService categoryService;

//     public CategoryController(CategoryService categoryService) {
//         this.categoryService = categoryService;
//     }

//     /**
//      * Lấy cây danh mục sản phẩm
//      * GET /api/categories/tree?type=PRODUCT
//      */
//     @GetMapping("/tree")
//     public List<CategoryTreeDto> getCategoryTree(
//             @RequestParam(defaultValue = "PRODUCT") String type) {

//         return categoryService.getCategoryTree(type);
//     }
// }
