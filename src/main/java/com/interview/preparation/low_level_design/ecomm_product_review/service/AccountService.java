package com.interview.preparation.low_level_design.ecomm_product_review.service;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.User;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.VerificationStatus;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import com.interview.preparation.low_level_design.ecomm_product_review.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private final AccountRepository accountRepository;
    private final ReviewService reviewService;

    public AccountService(AccountRepository accountRepository ,ReviewService reviewService ){
        this.accountRepository = accountRepository;
        this.reviewService = reviewService;
    }

    public User addUser(User user){
        return accountRepository.addUser(user);
    }

    public User getUserByUserId(String userId) throws UserNotFoundException {
        return accountRepository.getUserByUserId(userId);
    }

    public User getUserByEmailId(String emailId){
        return accountRepository.getUserByEmailId(emailId);
    }

    public List<Review> getUserReviews(User user){
        List<Review> reviews = reviewService.getReviewList()
                .stream()
                .filter(u -> u.getUserId().equalsIgnoreCase(user.getUserId()))
                .collect(Collectors.toList());
        return reviews;
    }

    public void initiateVerification(User user) throws UserNotFoundException {
        //some logic to initiate verification process;
        //update the verification info for the user object
        setVerificationStatus(user.getUserId(), VerificationStatus.VERIFIED);
    }

    public void setVerificationStatus(String userId, VerificationStatus verificationStatus) throws UserNotFoundException {
        accountRepository.getUserByUserId(userId).getUserProfile().setVerificationStatus(verificationStatus);
    }
}
