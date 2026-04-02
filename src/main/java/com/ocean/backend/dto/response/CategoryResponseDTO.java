package com.ocean.backend.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private String slug;
    private String type;
    private String status;
    private Long parentId;
    private String parentName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}