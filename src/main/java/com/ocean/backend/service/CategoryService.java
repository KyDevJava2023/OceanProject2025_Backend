// package com.ocean.backend.service;

// import com.ocean.backend.dto.CategoryCreateRequest;
// import com.ocean.backend.dto.CategoryTreeDto;
// import com.ocean.backend.dto.CategoryUpdateRequest;
// import com.ocean.backend.entity.Category;
// import com.ocean.backend.repository.CategoryRepository;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.*;

// @Service
// public class CategoryService {

//     private final CategoryRepository categoryRepository;

//     public CategoryService(CategoryRepository categoryRepository) {
//         this.categoryRepository = categoryRepository;
//     }

//     public Category create(CategoryCreateRequest req) {
//         Category c = new Category();
//         c.setName(req.getName());
//         c.setSlug(req.getSlug());
//         c.setType(req.getType());
//         c.setStatus("ACTIVE");
//         c.setCreatedAt(LocalDateTime.now());
//         c.setUpdatedAt(LocalDateTime.now());

//         if (req.getParentId() != null) {
//             Category parent = categoryRepository.findById(req.getParentId())
//                     .orElseThrow(() -> new RuntimeException("PARENT NOT FOUND"));
//             c.setParent(parent);
//         }

//         return categoryRepository.save(c);
//     }

//     public Category update(Long id, CategoryUpdateRequest req) {
//         Category c = categoryRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));

//         if (req.getName() != null)
//             c.setName(req.getName());
//         if (req.getSlug() != null)
//             c.setSlug(req.getSlug());
//         if (req.getType() != null)
//             c.setType(req.getType());
//         if (req.getStatus() != null)
//             c.setStatus(req.getStatus());
//         c.setUpdatedAt(LocalDateTime.now());

//         if (req.getParentId() != null) {
//             Category parent = categoryRepository.findById(req.getParentId())
//                     .orElseThrow(() -> new RuntimeException("PARENT NOT FOUND"));
//             c.setParent(parent);
//         }

//         return categoryRepository.save(c);
//     }

//     public void delete(Long id) {
//         categoryRepository.deleteById(id);
//     }

//     public Category toggleStatus(Long id) {
//         Category c = categoryRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));

//         if ("ACTIVE".equals(c.getStatus())) {
//             c.setStatus("INACTIVE");
//         } else {
//             c.setStatus("ACTIVE");
//         }

//         return categoryRepository.save(c);
//     }

//     /**
//      * Build cây danh mục (menu)
//      * GET /api/categories/tree?type=PRODUCT
//      */
//     public List<CategoryTreeDto> getCategoryTree(String type) {

//         // 1. Lấy toàn bộ category theo type
//         List<Category> categories = categoryRepository.findByTypeAndStatus(type, "ACTIVE");

//         // 2. Map id -> DTO
//         Map<Long, CategoryTreeDto> map = new HashMap<>();

//         for (Category c : categories) {
//             CategoryTreeDto dto = new CategoryTreeDto();
//             dto.setId(c.getId());
//             dto.setName(c.getName());
//             dto.setSlug(c.getSlug());
//             map.put(c.getId(), dto);
//         }

//         // 3. Build tree
//         List<CategoryTreeDto> roots = new ArrayList<>();

//         for (Category c : categories) {
//             if (c.getParent() == null) {
//                 roots.add(map.get(c.getId()));
//             } else {
//                 CategoryTreeDto parent = map.get(c.getParent().getId());
//                 parent.getChildren().add(map.get(c.getId()));
//             }
//         }

//         return roots;
//     }
// }
