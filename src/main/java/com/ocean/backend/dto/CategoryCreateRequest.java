package com.ocean.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private String type; // "PRODUCT", "POST", ...

    private Long parentId;
}

