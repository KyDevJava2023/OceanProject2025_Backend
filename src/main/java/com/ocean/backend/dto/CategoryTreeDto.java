package com.ocean.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryTreeDto {

    private Long id;
    private String name;
    private String slug;

    private List<CategoryTreeDto> children = new ArrayList<>();
}
