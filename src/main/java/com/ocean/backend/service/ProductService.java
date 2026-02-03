// package com.ocean.backend.service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.ocean.backend.dto.ProductDetailDto;
// import com.ocean.backend.dto.ProductListDto;
// import com.ocean.backend.entity.Product;
// import com.ocean.backend.repository.CategoryRepository;
// import com.ocean.backend.repository.ProductRepository;

// @Service
// public class ProductService {

//     private final ProductRepository productRepository;
//     private final CategoryRepository categoryRepository;

//     public ProductService(
//             ProductRepository productRepository,
//             CategoryRepository categoryRepository
//     ) {
//         this.productRepository = productRepository;
//         this.categoryRepository = categoryRepository;
//     }
    
//     /**
//      * Danh sách sản phẩm theo category (SEO page)
//      */
//     public List<ProductListDto> getProductsByCategorySlug(String categorySlug) {

//         // 1. Đảm bảo category tồn tại
//         categoryRepository.findBySlugAndTypeAndStatus(
//                 categorySlug,
//                 "PRODUCT",
//                 "ACTIVE"
//         ).orElseThrow(() -> new RuntimeException("CATEGORY NOT FOUND"));

//         // 2. Lấy product
//         return productRepository.findByCategorySlug(categorySlug)
//                 .stream()
//                 .map(this::toListDto)
//                 .collect(Collectors.toList());
//     }

//     /**
//      * Chi tiết sản phẩm
//      */
//     public ProductDetailDto getProductDetail(String slug) {

//         Product product = productRepository
//                 .findBySlugAndStatus(slug, "ACTIVE")
//                 .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

//         return toDetailDto(product);
//     }

//     private ProductListDto toListDto(Product p) {
//         ProductListDto dto = new ProductListDto();
//         dto.setId(p.getId());
//         dto.setName(p.getName());
//         dto.setSlug(p.getSlug());
//         dto.setPrice(p.getPrice());
//         dto.setThumbnail(p.getThumbnail());
//         return dto;
//     }
//     public List<ProductListDto> findAll() {
//     return productRepository
//             .findByStatus("ACTIVE")
//             .stream()
//             .map(this::toListDto)
//             .collect(Collectors.toList());
// }
//     private ProductDetailDto toDetailDto(Product p) {
//         ProductDetailDto dto = new ProductDetailDto();
//         dto.setId(p.getId());
//         dto.setName(p.getName());
//         dto.setSlug(p.getSlug());
//         dto.setPrice(p.getPrice());
//         dto.setThumbnail(p.getThumbnail());
//         dto.setContent(p.getContent());
//         return dto;
//     }
// }
