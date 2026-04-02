package com.ocean.backend.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotBlank(message = "Slug không được để trống")
    private String slug;

    private String shortDescription;
    private String content;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private BigDecimal price;

    private String thumbnail;
    private Boolean isBestSeller = false;
    private String status = "ACTIVE";

    @NotNull(message = "Danh mục không được để trống")
    private Long categoryId;
}