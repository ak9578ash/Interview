package com.interview.preparation.low_level_design.interview.e_commerce.service;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.CategoryNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Category;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Product;

import java.util.List;

public interface CategoryService {
    List<Product> addProductToCategory(Category category, List<Product>productList);
    Category deleteCategory(Category category) throws CategoryNotFoundException;
}
