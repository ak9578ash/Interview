package com.interview.preparation.low_level_design.bill_sharing.model.strategy.impl;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.model.strategy.BifurcateStrategy;
import com.interview.preparation.low_level_design.bill_sharing.model.strategy.UserBifurcateShare;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.PercentageSplit;

import java.util.ArrayList;
import java.util.List;

public class PercentageBifurcateStrategyImpl implements BifurcateStrategy {
    private final List<PercentageSplit>percentageSplits ;

    public PercentageBifurcateStrategyImpl(List<PercentageSplit> percentageSplits) {
        this.percentageSplits = percentageSplits;
    }

    @Override
    public List<UserBifurcateShare> bifurcate(Expense expense, List<User> users) {
        List<UserBifurcateShare>userBifurcateShares = new ArrayList<>();
        for (PercentageSplit percentageSplit : percentageSplits) {
            User user = percentageSplit.getUser();
            double amount = (percentageSplit.getPercentage() / 100) * expense.getExpenseAmount();

            userBifurcateShares.add(new UserBifurcateShare(user, amount));
        }
        return userBifurcateShares;
    }
}
