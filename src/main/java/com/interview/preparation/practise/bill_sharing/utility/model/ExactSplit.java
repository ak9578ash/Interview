package com.interview.preparation.practise.bill_sharing.utility.model;


import com.interview.preparation.practise.bill_sharing.model.User;
import lombok.Getter;

@Getter
public class ExactSplit{
    private final User user;
    private final Double splitAmount;

    public ExactSplit(User user, Double splitAmount) {
        this.user = user;
        this.splitAmount = splitAmount;
    }
}
