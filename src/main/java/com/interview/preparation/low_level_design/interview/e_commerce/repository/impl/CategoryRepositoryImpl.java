package com.interview.preparation.low_level_design.interview.e_commerce.repository.impl;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.CategoryNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Category;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Product;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepositoryImpl implements CategoryRepository {
    public static Map<Category, List<Product>> categoryToProductListMap = new HashMap<>();

    @Override
    public List<Product> addProductToCategory(Category category , List<Product>productList) {
        categoryToProductListMap.putIfAbsent(category , new ArrayList<>());
        categoryToProductListMap.get(category).addAll(productList);
        return productList;
    }

    @Override
    public Category deleteCategory(Category category) throws CategoryNotFoundException {
        if(!categoryToProductListMap.containsKey(category)){
            throw new CategoryNotFoundException("provided category is not valid");
        }
        categoryToProductListMap.remove(category);
        return category;
    }
}
