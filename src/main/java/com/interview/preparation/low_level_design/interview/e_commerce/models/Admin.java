package com.interview.preparation.low_level_design.interview.e_commerce.models;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.CategoryNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.service.CategoryService;
import com.interview.preparation.low_level_design.interview.e_commerce.service.ProductService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Admin extends User{
    private final ProductService productService;
    private final CategoryService categoryService;


    public Admin(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    public Product addProduct(Product product){
        return productService.addProduct(product);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {
        return productService.updateProduct(product);
    }

    public Product deleteProduct(Product product) throws ProductNotFoundException {
        return productService.deleteProduct(product);
    }

    public List<Product> addProductToCategoryCategory(Category category , List<Product> productList){
        return categoryService.addProductToCategory(category,productList);
    }

    public Category deleteCategory(Category category) throws CategoryNotFoundException {
        return categoryService.deleteCategory(category);
    }
}
