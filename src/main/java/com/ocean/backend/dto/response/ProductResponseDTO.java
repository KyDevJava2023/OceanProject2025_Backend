package com.ocean.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String slug;
    private String shortDescription;
    private String content;
    private BigDecimal price;
    private String thumbnail;
    private Boolean isBestSeller;
    private String status;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}