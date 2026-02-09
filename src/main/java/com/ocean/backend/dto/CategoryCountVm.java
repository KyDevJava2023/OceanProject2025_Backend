package com.ocean.backend.dto;

public record CategoryCountVm(
        Long id,
        String name,
        String slug,
        long count
) {}
