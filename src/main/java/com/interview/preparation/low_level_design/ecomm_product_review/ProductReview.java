package com.interview.preparation.low_level_design.ecomm_product_review;


import com.interview.preparation.low_level_design.ecomm_product_review.exception.InvalidRatingException;
import com.interview.preparation.low_level_design.ecomm_product_review.exception.ProductNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.Address;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.User;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.UserProfile;
import com.interview.preparation.low_level_design.ecomm_product_review.model.product.Product;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Feature;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Meta;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import com.interview.preparation.low_level_design.ecomm_product_review.model.summary.Summary;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.AccountRepository;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.ProductRepository;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.ReviewRepository;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.SummaryRepository;
import com.interview.preparation.low_level_design.ecomm_product_review.service.AccountService;
import com.interview.preparation.low_level_design.ecomm_product_review.service.ProductService;
import com.interview.preparation.low_level_design.ecomm_product_review.service.ReviewService;
import com.interview.preparation.low_level_design.ecomm_product_review.service.SummaryService;

import java.util.List;
import java.util.Stack;

public class ProductReview {
    public static AccountRepository accountRepository;
    public static AccountService accountService ;
    public static ReviewRepository reviewRepository;
    public static ReviewService reviewService;

    public static ProductRepository productRepository;
    public static ProductService productService;

    public static SummaryRepository summaryRepository;
    public static SummaryService summaryService;

    public static void main(String[] args) throws UserNotFoundException, ProductNotFoundException, InvalidRatingException {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);

        summaryRepository = new SummaryRepository();
        summaryService = new SummaryService(summaryRepository ,productService);

        reviewRepository = new ReviewRepository();
        reviewService = new ReviewService(reviewRepository, productService,summaryService);

        accountRepository = new AccountRepository();
        accountService = new AccountService(accountRepository,reviewService);




        Address userAddress = new Address("82 A Madhav Kunj","Pratap Nagar",
                "Agra","UP","282002");

        UserProfile userProfile = new UserProfile();
        userProfile.setEmailId("akashgupta9578@gmail.com");
        userProfile.setFirstName("Akash");
        userProfile.setLastName("Gupta");
        userProfile.setUserName("akashgupta9578");
        userProfile.setPassword("********");
        userProfile.setPhoneNumber("79060489");
        userProfile.setUserAddress(userAddress);

        // create a user --> TEST CASE 1
        User user = new User(userProfile);

        // persist the user --> TEST CASE 2
        accountService.addUser(user);
        accountService.initiateVerification(user);

        // create a product --> TEST CASE 3
        Product iphone = new Product("iphone-12");
        productService.addProduct(iphone);


        // create features for above product --> TEST CASE 4
        Feature f1 = new Feature("camera","Camera Review","good camera",4);
        Meta cameraMeta = new Meta("path-to-f1-image");
        f1.getMetas().add(cameraMeta);

        Feature f2 = new Feature("display","Display Review","good retina display",5);
        Meta displayMeta = new Meta("path-to-f2-image");
        f2.getMetas().add(displayMeta);

        // create review for above product --> TEST CASE 5
        Review iphoneReview1 = new Review(iphone.getProductId() , user.getUserId(),4);
        Meta iphoneMeta = new Meta("path-to-iphone-image-by-user-1");

        iphoneReview1.setTitle("iphone-12 review");
        iphoneReview1.setText("good phone");
        iphoneReview1.getMetas().add(iphoneMeta);
        iphoneReview1.getFeatures().add(f1);
        iphoneReview1.getFeatures().add(f2);

        reviewService.addReview(iphoneReview1);


        Address userAddress2 = new Address("xyz madhav ","Jaipur house",
                "Agra","UP","282002");

        UserProfile userProfile2 = new UserProfile();
        userProfile2.setEmailId("abcxyz@gmail.com");
        userProfile2.setFirstName("abc");
        userProfile2.setLastName("xyz");
        userProfile2.setUserName("abcxyz");
        userProfile2.setPassword("********");
        userProfile2.setPhoneNumber("1234567890");
        userProfile2.setUserAddress(userAddress2);

        User user2 = new User(userProfile2);

        accountService.addUser(user2);
        accountService.initiateVerification(user2);

        Feature f3 = new Feature("camera","Camera Review","good camera with crystal clear quality",5);

        Feature f4 = new Feature("body","Body Review","good glass finish",5);
        Meta bodyMeta = new Meta("path-to-f4-image");
        f4.getMetas().add(bodyMeta);

        Review iphoneReview2 = new Review(iphone.getProductId() , user2.getUserId(),5);
        Meta iphoneMeta2 = new Meta("path-to-iphone-image-by-user-2");

        iphoneReview2.setTitle("iphone-12 review");
        iphoneReview2.setText("good phone must buy in this price range");
        iphoneReview2.getMetas().add(iphoneMeta2);
        iphoneReview2.getFeatures().add(f3);
        iphoneReview2.getFeatures().add(f4);

        reviewService.addReview(iphoneReview2);


        Address userAddress3 = new Address("xyz madhav ","Jaipur house",
                "Agra","UP","282002");

        UserProfile userProfile3 = new UserProfile();
        userProfile3.setEmailId("abcxyz@gmail.com");
        userProfile3.setFirstName("abc");
        userProfile3.setLastName("xyz");
        userProfile3.setUserName("abcxyz");
        userProfile3.setPassword("********");
        userProfile3.setPhoneNumber("1234567890");
        userProfile3.setUserAddress(userAddress3);

        User user3 = new User(userProfile3);

        accountService.addUser(user3);
        accountService.initiateVerification(user3);

        Review iphoneReview3 = new Review(iphone.getProductId() , user3.getUserId(),1);
        reviewService.addReview(iphoneReview3);

        // get reviews of product --> TEST CASE 6
        List<Review> productReviewsById = productService.getReviewsOfProduct(iphone.getProductId());
        for(Review productReviewById : productReviewsById){
            System.out.println(productReviewById.getText());
        }

        // get reviews of feature --> TEST CASE 7
        List<Feature> productReviewsByFeature = productService.getReviewsOfFeature(iphoneReview1.getProductId(),"camera");
        for(Feature productReviewByFeature : productReviewsByFeature){
            System.out.println(productReviewByFeature.getRating());
        }

        // get reviews of product by date desc--> TEST CASE 8
        List<Review> productReviewsByIdByDateDesc = productService.getReviewsOfProductByDateDesc(iphone.getProductId());
        for(Review productReviewByIdByDateDesc : productReviewsByIdByDateDesc){
            System.out.println(productReviewByIdByDateDesc.getText());
        }

        // get reviews of product by rating --> TEST CASE 9
        List<Review> productReviewsByRating = productService.getReviewsOfProductByRating(iphone.getProductId(),5);
        for(Review productReviewByRating : productReviewsByRating){
            System.out.println(productReviewByRating.getText());
        }

        // get summary of the product --> TEST CASE 10
        Summary iphoneSummary = summaryService.getSummary(iphone);


    }
}
