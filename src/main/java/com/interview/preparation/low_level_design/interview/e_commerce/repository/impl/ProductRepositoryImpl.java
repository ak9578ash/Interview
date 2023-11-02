package com.interview.preparation.low_level_design.interview.e_commerce.repository.impl;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.CategoryType;
import com.interview.preparation.low_level_design.interview.e_commerce.models.Product;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    public static List<Product>productList = new ArrayList<>();
    @Override
    public Product addProduct(Product product) {
       productList.add(product);
       return product;
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
      for(int i=0;i<productList.size();i++){
          if(productList.get(i).getId().equals(product.getId())){
              productList.remove(i);
              productList.add(product);
              return product;
          }
      }
       throw new ProductNotFoundException("provided product is not valid");
    }

    @Override
    public Product deleteProduct(Product product) throws ProductNotFoundException {
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).getId().equals(product.getId())){
                productList.remove(i);
                return product;
            }
        }
        throw new ProductNotFoundException("provided product is not valid");
    }

    @Override
    public List<Product> searchProductByName(String name) {
        List<Product>fetchedProducts = new ArrayList<>();
        for (int i=0;i<productList.size();i++) {
            if (productList.get(i).getName().equals(name)) {
                fetchedProducts.add(productList.get(i));
            }
        }
        return fetchedProducts;
    }

    @Override
    public List<Product> searchProductByCategoryType(CategoryType categoryType) {
        List<Product>fetchedProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategoryType().equals(categoryType)) {
                fetchedProducts.add(product);
            }
        }
        return fetchedProducts;
    }
}
