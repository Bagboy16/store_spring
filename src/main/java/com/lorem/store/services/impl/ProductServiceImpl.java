package com.lorem.store.services.impl;

import com.lorem.store.domain.entities.CategoryEntity;
import com.lorem.store.domain.entities.ProductEntity;
import com.lorem.store.repositories.CategoryRepository;
import com.lorem.store.repositories.ProductRepository;
import com.lorem.store.services.ProductService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        CategoryEntity categoryId = productEntity.getCategory();
        categoryId = categoryRepository.findById(categoryId.getId()).orElse(null);
        if (categoryId == null){
            log.severe("Category not found");
            return null;
        }
        productEntity.setCategory(categoryId);
        return productRepository.save(productEntity);
    }
}
