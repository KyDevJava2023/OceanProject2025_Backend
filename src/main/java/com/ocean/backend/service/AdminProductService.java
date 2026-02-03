// package com.ocean.backend.service;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.stereotype.Service;

// import com.ocean.backend.dto.ProductCreateRequest;
// import com.ocean.backend.dto.ProductUpdateRequest;
// import com.ocean.backend.entity.Category;
// import com.ocean.backend.entity.Product;
// import com.ocean.backend.repository.CategoryRepository;
// import com.ocean.backend.repository.ProductRepository;

// @Service
// public class AdminProductService {

//     private final ProductRepository productRepository;
//     private final CategoryRepository categoryRepository;

//     public AdminProductService(
//             ProductRepository productRepository,
//             CategoryRepository categoryRepository) {
//         this.productRepository = productRepository;
//         this.categoryRepository = categoryRepository;
//     }

//     // CREATE
//     public Product create(ProductCreateRequest req) {

//         // VALIDATE
//         if (req.getName() == null || req.getName().isBlank()) {
//             throw new RuntimeException("NAME IS REQUIRED");
//         }
//         if (req.getSlug() == null || req.getSlug().isBlank()) {
//             throw new RuntimeException("SLUG IS REQUIRED");
//         }
//         if (req.getPrice() == null) {
//             throw new RuntimeException("PRICE IS REQUIRED");
//         }
//         if (req.getCategoryIds() == null || req.getCategoryIds().isEmpty()) {
//             throw new RuntimeException("CATEGORY IDS MUST NOT BE NULL");
//         }

//         Product product = new Product();
//         product.setName(req.getName());
//         product.setSlug(req.getSlug());
//         product.setPrice(req.getPrice());
//         product.setContent(req.getContent());
//         product.setThumbnail(req.getThumbnail());
//         product.setStatus("ACTIVE");

//         Set<Category> categories = new HashSet<>(
//                 categoryRepository.findAllById(req.getCategoryIds()));
//         product.setCategories(categories);

//         return productRepository.save(product);
//     }

//     // UPDATE
//     public Product update(Long id, ProductUpdateRequest req) {

//         Product product = productRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

//         product.setName(req.getName());
//         product.setContent(req.getContent());
//         product.setThumbnail(req.getThumbnail());
//         product.setStatus(req.getStatus());

//         if (req.getCategoryIds() != null) {
//             Set<Category> categories = new HashSet<>(
//                     categoryRepository.findAllById(req.getCategoryIds()));
//             product.setCategories(categories);
//         }

//         return productRepository.save(product);
//     }

//     // DELETE
//     public void delete(Long id) {
//         productRepository.deleteById(id);
//     }
// }
