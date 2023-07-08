package com.interview.preparation.low_level_design.bill_sharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserShare {
    private String emailId;
    private double share;
    List<Contribution> contributions;

    public UserShare( String emailId ,Double share){
        this.share = share;
        this.emailId = emailId;
        this.contributions = new ArrayList<>();
    }
}
