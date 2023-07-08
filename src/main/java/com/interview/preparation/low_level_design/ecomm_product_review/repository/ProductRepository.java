package com.interview.preparation.low_level_design.ecomm_product_review.repository;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    public static List<Product> products = new ArrayList<>();
    public static Map<String,Product> productMap = new HashMap<>();

    public Product addProduct(Product product){
        productMap.putIfAbsent(product.getProductId() , product);
        products.add(product);
        return product;
    }

    public Product getProductById(String productId) throws ProductNotFoundException {
        Product product =  productMap.get(productId);
        if(product == null){
            throw new ProductNotFoundException(String.format("product with this %s product id not found",productId));
        }
        return product;
    }
}
