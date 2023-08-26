package com.interview.preparation.low_level_design.bill_sharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ExpenseGroup {
    private Set<User> groupMembers;
    private String expenseGroupId;
    private Map<String, UserShare> userContributions;

    public ExpenseGroup(){
        this.expenseGroupId = UUID.randomUUID().toString();
        this.groupMembers = new HashSet<>();
        this.userContributions = new HashMap<>();
    }
}
