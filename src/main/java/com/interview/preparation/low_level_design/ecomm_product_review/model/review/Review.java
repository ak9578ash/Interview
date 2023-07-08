package com.interview.preparation.low_level_design.ecomm_product_review.model.review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Review {
    private String reviewId;
    private String productId;
    private String userId;
    private int rating;
    private String title;
    private String text;
    private LocalDateTime reviewedDate;

    private List<Meta> metas;
    public List<Feature> features;
    private ReviewState reviewState;
    private ReviewType reviewType;

    public Review(String productId , String userId , int rating){
        this.reviewId = UUID.randomUUID().toString();
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.reviewedDate = LocalDateTime.now();
        this.metas = new ArrayList<>();
        this.features = new ArrayList<>();
    }
}
