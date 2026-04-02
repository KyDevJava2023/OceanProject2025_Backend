package com.ocean.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    @NotBlank(message = "Slug không được để trống")
    private String slug;

    @NotBlank(message = "Type không được để trống")
    private String type;

    private Long parentId;
    private String status = "ACTIVE";
}