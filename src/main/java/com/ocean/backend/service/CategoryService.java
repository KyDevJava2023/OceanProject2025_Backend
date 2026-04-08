package com.ocean.backend.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ocean.backend.entity.Category;
import com.ocean.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoryTree() {
    return categoryRepository.findTree("PRODUCT");
}
   
}
