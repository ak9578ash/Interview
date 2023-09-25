package com.interview.preparation.practise.bill_sharing.model;

import lombok.Getter;

import java.util.*;

@Getter
public class ExpenseGroup {
    private final String id;
    private final Set<User> groupMember;
    private final Map<String, UserShare> userContribution;

    public ExpenseGroup() {
        this.id = UUID.randomUUID().toString();
        this.groupMember = new HashSet<>();
        this.userContribution = new HashMap<>();
    }
}
