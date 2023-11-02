package com.interview.preparation.low_level_design.interview.e_commerce.service.impl;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.CategoryType;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Product;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.ProductRepository;
import com.interview.preparation.low_level_design.interview.e_commerce.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        return productRepository.updateProduct(product);
    }

    @Override
    public Product deleteProduct(Product product) throws ProductNotFoundException {
        return productRepository.deleteProduct(product);
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return productRepository.searchProductByName(name);
    }

    @Override
    public List<Product> searchProductByCategoryType(CategoryType categoryType) {
        return productRepository.searchProductByCategoryType(categoryType);
    }
}
