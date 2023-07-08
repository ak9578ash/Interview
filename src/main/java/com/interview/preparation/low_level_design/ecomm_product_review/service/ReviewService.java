package com.interview.preparation.low_level_design.ecomm_product_review.service;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.InvalidRatingException;
import com.interview.preparation.low_level_design.ecomm_product_review.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.User;
import com.interview.preparation.low_level_design.ecomm_product_review.model.product.Product;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.ReviewState;
import com.interview.preparation.low_level_design.ecomm_product_review.model.summary.Summary;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.ReviewRepository;
import lombok.NonNull;

import java.util.List;

import static com.interview.preparation.low_level_design.ecomm_product_review.model.review.ReviewType.CERTIFIED_BUYER;

public class ReviewService {
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private SummaryService summaryService;
    public ReviewService(ReviewRepository reviewRepository, ProductService productService , SummaryService summaryService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.summaryService = summaryService;
    }


    public Review addReview(Review review) throws InvalidRatingException, ProductNotFoundException {
        if (review.getRating() <= 0 || review.getRating() > 5) {
            throw new InvalidRatingException("provided rating is not applicable");
        }
        Product product = productService.getProductById(review.getProductId());
        reviewRepository.addReview(review);
        product.getReviews().add(review);

        moderate(review);
        updateReviewType(review);

        // create the summary
        Summary productSummary = summaryService.createSummary(product);
        summaryService.addSummary(productSummary);
        return review;
    }

    private void updateReviewType(Review review) {
        //some logic to check the review authenticity

        //if the user has ordered the product then the review type is certified
        //else anonymous
        review.setReviewType(CERTIFIED_BUYER);
    }

    public Review moderate(@NonNull Review review) {
        //some logic to check the  review  moderation

        //if the moderation succeeds then make the review available for users
        // else failed
        review.setReviewState(ReviewState.MODERATION_PASSED);
        return review;
    }

    public Review getReviewByReviewId(String reviewId) {
        return reviewRepository.getReviewByReviewId(reviewId);
    }

    public List<Review> getReviewList() {
        return reviewRepository.getReviewList();
    }

}
