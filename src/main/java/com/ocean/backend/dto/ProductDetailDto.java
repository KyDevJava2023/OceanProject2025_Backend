package com.ocean.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetailDto {
    private Long id;
    private String name;
    private String slug;
    private BigDecimal price;
    private String thumbnail;
    private String content;
}
