package com.interview.preparation.low_level_design.bill_sharing.model.strategy;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;

import java.util.List;

public interface BifurcateStrategy {
    List<UserBifurcateShare> bifurcate(Expense expense , List<User>users);
}
