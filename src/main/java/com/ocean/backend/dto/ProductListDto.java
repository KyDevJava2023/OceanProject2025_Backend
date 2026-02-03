// package com.ocean.backend.dto;

// import java.math.BigDecimal;

// import com.ocean.backend.entity.Product;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class ProductListDto {
    
//     private Long id;
//     private String name;
//     private String slug;
//     private BigDecimal price;
//     private String thumbnail;
//     private String status;
    
//     /**
//      * Constructor chuyển đổi từ Entity sang DTO
//      */
//     public ProductListDto(Product product) {
//         this.id = product.getId();
//         this.name = product.getName();
//         this.slug = product.getSlug();
//         this.price = product.getPrice();
//         this.thumbnail = product.getThumbnail();
//         this.status = product.getStatus();
//     }
// }