package com.interview.preparation.low_level_design.interview.e_commerce.repository;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.CategoryType;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Product;

import java.util.List;

public interface ProductRepository {
    Product addProduct(Product product);
    Product updateProduct(Product product) throws ProductNotFoundException;
    Product deleteProduct(Product product) throws ProductNotFoundException;
    List<Product> searchProductByName(String name);
    List<Product> searchProductByCategoryType(CategoryType categoryType);
}
