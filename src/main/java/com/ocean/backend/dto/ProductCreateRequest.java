package com.ocean.backend.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    private String name;
    private String slug;
    private BigDecimal price;
    private String content;
    private String thumbnail;
    private Set<Long> categoryIds;
}
