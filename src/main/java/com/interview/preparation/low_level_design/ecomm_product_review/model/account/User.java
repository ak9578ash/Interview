package com.interview.preparation.low_level_design.ecomm_product_review.model.account;

import com.interview.preparation.low_level_design.ecomm_product_review.service.AccountService;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class User {
    private String userId;
    private String nickName;
    private UserProfile userProfile;

    public User(UserProfile userProfile){
        this.userId = UUID.randomUUID().toString();
        this.userProfile = userProfile;
    }

    public User(String nickName , UserProfile userProfile){
        this.userId = UUID.randomUUID().toString();
        this.nickName = nickName;
        this.userProfile = userProfile;
    }
}
