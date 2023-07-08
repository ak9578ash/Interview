package com.interview.preparation.low_level_design.ecomm_product_review.model.product;

import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Product {
    private String productId;
    private String summaryId;
    private String productName;
    private List<Review> reviews;

    public Product(String productName){
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.reviews = new ArrayList<>();
    }


}
