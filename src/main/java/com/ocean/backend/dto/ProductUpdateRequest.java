package com.ocean.backend.dto;

import java.util.Set;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private String name;
    private String price;
    private String content;
    private String thumbnail;
    private Set<Long> categoryIds;
    private String status; // ACTIVE / INACTIVE
}
