package com.lorem.store.controllers;

import com.lorem.store.domain.dto.CategoryDto;
import com.lorem.store.domain.entities.CategoryEntity;
import com.lorem.store.mappers.impl.CategoryMapper;
import com.lorem.store.services.CategoryService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class CategoryController {

    private CategoryService categoryService;

    private CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/api/categories")
    public CategoryDto retrieveCategory() {
        return new CategoryDto();
    }

    @PostMapping("/api/categories")
    public CategoryDto createCategory(@RequestBody final CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity createdCategory = categoryService.createCategory(categoryEntity);
        return categoryMapper.mapTo(createdCategory);
    }
}
