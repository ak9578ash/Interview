package com.interview.preparation.practise.bill_sharing.utility.model;


import com.interview.preparation.practise.bill_sharing.model.User;
import lombok.Getter;

@Getter
public class PercentageSplit {
    private final User user;
    private final Double percentageSplit;

    public PercentageSplit(User user, Double percentageSplit) {
        this.user = user;
        this.percentageSplit = percentageSplit;
    }
}
