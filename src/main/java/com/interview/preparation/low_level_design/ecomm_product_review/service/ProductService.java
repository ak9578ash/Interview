package com.interview.preparation.low_level_design.ecomm_product_review.service;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.product.Product;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Feature;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.ReviewState;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.ReviewType;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public Product getProductById(String productId) throws ProductNotFoundException {
        return productRepository.getProductById(productId);
    }

    public List<Review> getReviewsOfProduct(String productId) throws ProductNotFoundException {

        return productRepository.getProductById(productId)
                .getReviews()
                .stream()
                .filter(r -> r.getReviewState() == ReviewState.MODERATION_PASSED)
                .collect(Collectors.toList());
    }

    public List<Feature> getReviewsOfFeature(String productId, String featureName) throws ProductNotFoundException {
        List<Review>productReviews = productRepository.getProductById(productId)
                .getReviews()
                .stream()
                .filter(r -> r.getReviewState() == ReviewState.MODERATION_PASSED)
                .toList();
        List<Feature> featureReviews = productReviews
                .stream()
                .flatMap(r -> r.getFeatures().stream())
                .collect(Collectors.toList());
        return featureReviews
                .stream()
                .filter(f -> f.getFeatureName().equalsIgnoreCase(featureName))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsOfProductByDateDesc(String productId) throws ProductNotFoundException {
        return productRepository.getProductById(productId)
                .getReviews()
                .stream()
                .filter(r -> r.getReviewState() == ReviewState.MODERATION_PASSED)
                .sorted(Comparator.comparing(Review :: getReviewedDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Review> getReviewByCertifiedBuyers(String productId) throws ProductNotFoundException {
        return productRepository.getProductById(productId).getReviews()
                .stream()
                .filter(r -> r.getReviewState() == ReviewState.MODERATION_PASSED
                        && r.getReviewType() == ReviewType.CERTIFIED_BUYER)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsOfProductByRating(String productId, int star) throws ProductNotFoundException {
        return productRepository.getProductById(productId).getReviews()
                .stream()
                .filter(r -> r.getRating() >= star)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsWithMeta(String productId ) throws ProductNotFoundException {
        return productRepository.getProductById(productId).getReviews()
                .stream()
                .filter(r -> r.getReviewState() ==ReviewState.MODERATION_PASSED
                        && isMetaPresent(r))
                .collect(Collectors.toList());
    }

    private boolean isMetaPresent(Review review) {
        if (review.getMetas() != null && review.getMetas().size() > 0)
            return true;
        for (Feature feature : review.features) {
            if (feature.getMetas() != null && feature.getMetas().size() > 0)
                return true;
        }
        return false;
    }



}
