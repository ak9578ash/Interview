package com.interview.preparation.low_level_design.interview.e_commerce;

import com.interview.preparation.low_level_design.interview.e_commerce.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.interview.e_commerce.models.*;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.CategoryRepository;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.ProductRepository;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.impl.CategoryRepositoryImpl;
import com.interview.preparation.low_level_design.interview.e_commerce.repository.impl.ProductRepositoryImpl;
import com.interview.preparation.low_level_design.interview.e_commerce.service.CategoryService;
import com.interview.preparation.low_level_design.interview.e_commerce.service.ProductService;
import com.interview.preparation.low_level_design.interview.e_commerce.service.impl.CategoryServiceImpl;
import com.interview.preparation.low_level_design.interview.e_commerce.service.impl.ProductServiceImpl;

import java.util.*;

public class EcommerceMain {
    static ProductRepository productRepository;
    static ProductService productService;
    static CategoryRepository categoryRepository;
    static CategoryService categoryService;

    public static void main(String[] args) throws ProductNotFoundException {
        productRepository = new ProductRepositoryImpl();
        productService = new ProductServiceImpl(productRepository);

        categoryRepository = new CategoryRepositoryImpl();
        categoryService = new CategoryServiceImpl(categoryRepository);

        Product p1 = new Product("abc",123.0,"nike", CategoryType.T_SHIRTS);
        Product p2 = new Product("def",123.0,"nike", CategoryType.TRACK_PANTS);
        Product p3 = new Product("qwe",12344.0,"puma",CategoryType.T_SHIRTS);

        Admin admin = new Admin(productService,categoryService);
        admin.setId(UUID.randomUUID().toString());
        admin.setName("Akash");
        admin.setEmailId("email@gmail.com");

        admin.addProduct(p1);
        admin.addProduct(p2);
        admin.addProduct(p3);

        p1.setSponsoredWeight(1);
        p3.setSponsoredWeight(3);

        admin.updateProduct(p1);
        admin.updateProduct(p3);

        List<Product>productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p3);
        admin.addProductToCategoryCategory(new Category(CategoryType.T_SHIRTS),productList);

        Customer customer1 = new Customer(productService);
        customer1.setId(UUID.randomUUID().toString());
        customer1.setName("test");
        customer1.setEmailId("cutomer@gmail.com");

//        List<Product> fetchedProductList = customer1.searchProductByName("abc");
//        for(int i=0;i<fetchedProductList.size();i++){
//            System.out.println(fetchedProductList.get(i).getName());
//        }
//        System.out.println("--------------------------------------------------");

        List<Product>fetchedProductList = customer1.searchProductByType(CategoryType.T_SHIRTS);
        for (Product product : fetchedProductList) {
            System.out.println(product.getName());
        }

//        List<String>v = new ArrayList<>();
//        v.add("UPI");
//        v.add("KARNATAKA");
//        v.add("BANGALORE");
//
//        Map<String , Integer>m = new HashMap<>();
//        String s = "";
//        for(int i=0;i<v.size();i++){
//            s = s + (v.get(i));
//            if(!m.containsKey(s)){
//                m.putIfAbsent(s,1);
//            }else{
//                Integer x = m.get(s);
//                m.put(s,x+1);
//            }
//            s = s + ("_");
//        }
//
//        for(Map.Entry<String,Integer>entry : m.entrySet()){
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
    }
}
