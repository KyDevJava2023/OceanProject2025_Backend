package com.ocean.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryUpdateRequest {
    private String name;
    private String slug;
    private String type;
    private Long parentId;
    private String status;
}

