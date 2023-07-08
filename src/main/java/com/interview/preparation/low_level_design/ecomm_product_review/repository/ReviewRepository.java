package com.interview.preparation.low_level_design.ecomm_product_review.repository;

import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewRepository {
    public static Map<String , Review> reviewMap = new HashMap<>();
    public static List<Review> reviews = new ArrayList<>();

    public Review addReview(Review review){
        reviewMap.putIfAbsent(review.getReviewId() , review);
        reviews.add(review);
        return review;
    }

    public Review getReviewByReviewId(String reviewId){
        return reviewMap.get(reviewId);
    }

    public List<Review>getReviewList(){
        return reviews;
    }
}
