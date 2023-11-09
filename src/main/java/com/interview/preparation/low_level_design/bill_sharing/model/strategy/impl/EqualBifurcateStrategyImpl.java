package com.interview.preparation.low_level_design.bill_sharing.model.strategy.impl;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.model.strategy.BifurcateStrategy;
import com.interview.preparation.low_level_design.bill_sharing.model.strategy.UserBifurcateShare;

import java.util.ArrayList;
import java.util.List;

public class EqualBifurcateStrategyImpl implements BifurcateStrategy {
    @Override
    public List<UserBifurcateShare> bifurcate(Expense expense , List<User>users) {
        double expenseAmount = expense.getExpenseAmount();
        double individualUserShare = expenseAmount/users.size();
        List<UserBifurcateShare>userBifurcateShares = new ArrayList<>();

        for (User user : users) {
            userBifurcateShares.add(new UserBifurcateShare(user, individualUserShare));
        }
        return userBifurcateShares;
    }
}
