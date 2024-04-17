package com.lorem.store.controllers;

import com.lorem.store.domain.dto.ProductDto;
import com.lorem.store.domain.entities.ProductEntity;
import com.lorem.store.mappers.impl.ProductMapper;
import com.lorem.store.services.ProductService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/api/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity createdProductEntity = productService.createProduct(productEntity);
        return productMapper.mapTo(createdProductEntity);
    }
}
