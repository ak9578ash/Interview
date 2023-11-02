package com.interview.preparation.low_level_design.interview.e_commerce.models;

import com.interview.preparation.low_level_design.interview.e_commerce.service.ProductService;

import java.util.List;

public class Customer extends User{
    private final ProductService productService;

    public Customer(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> searchProductByName(String name){
        List<Product>fetchedProduct = productService.searchProductByName(name);
        fetchedProduct.sort(new SortByWeight());
        return fetchedProduct;
    }

    public List<Product>searchProductByType(CategoryType categoryType){
        List<Product>fetchedProduct = productService.searchProductByCategoryType(categoryType);
        fetchedProduct.sort(new SortByWeight());
        return fetchedProduct;
    }
}
