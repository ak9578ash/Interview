package com.interview.preparation.low_level_design.interview.e_commerce.service.impl;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.CategoryNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Category;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Product;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.CategoryRepository;
import com.interview.preparation.low_level_design.interview.e_commerce.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> addProductToCategory(Category category, List<Product> productList) {
        return categoryRepository.addProductToCategory(category,productList);
    }

    @Override
    public Category deleteCategory(Category category) throws CategoryNotFoundException {
        return categoryRepository.deleteCategory(category);
    }
}
