package com.interview.preparation.low_level_design.ecomm_product_review.service;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.product.Product;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.ReviewState;
import com.interview.preparation.low_level_design.ecomm_product_review.model.summary.Summary;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.SummaryRepository;

import java.util.List;


public class SummaryService {
    private final SummaryRepository summaryRepository;
    private final ProductService productService;

    public SummaryService(SummaryRepository summaryRepository, ProductService productService) {
        this.summaryRepository = summaryRepository;
        this.productService = productService;
    }

    public Summary createSummary(Product product) throws ProductNotFoundException {
        List<Review> productReviews = productService.getProductById(product.getProductId())
                .getReviews()
                .stream()
                .filter(r -> r.getReviewState() == ReviewState.MODERATION_PASSED)
                .toList();

        Summary productSummary = new Summary(product.getProductId());
        productSummary.setTotalRatings(productReviews.size());
        product.setSummaryId(productSummary.getSummaryId());

        for (Review productReview : productReviews) {
            switch (productReview.getRating()) {
                case 1 -> productSummary.setTotalOneStars(productSummary.getTotalOneStars() + 1);
                case 2 -> productSummary.setTotalTwoStars(productSummary.getTotalTwoStars() + 1);
                case 3 -> productSummary.setTotalThreeStars(productSummary.getTotalThreeStars() + 1);
                case 4 -> productSummary.setTotalFourStars(productSummary.getTotalFourStars() + 1);
                default -> productSummary.setTotalFiveStars(productSummary.getTotalFiveStars() + 1);
            }
        }
        return productSummary;
    }

    public Summary addSummary(Summary summary){
        return summaryRepository.addSummary(summary);
    }

    public Summary getSummary(Product product){
        return summaryRepository.getSummaryById(product.getSummaryId());
    }
}
