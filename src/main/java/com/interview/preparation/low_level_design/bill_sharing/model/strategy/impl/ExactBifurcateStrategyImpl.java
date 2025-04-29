package com.interview.preparation.low_level_design.bill_sharing.model.strategy.impl;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.model.strategy.BifurcateStrategy;
import com.interview.preparation.low_level_design.bill_sharing.model.strategy.UserBifurcateShare;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.ExactSplit;

import java.util.ArrayList;
import java.util.List;

public class ExactBifurcateStrategyImpl implements BifurcateStrategy {
    private final List<ExactSplit> exactSplits;

    public ExactBifurcateStrategyImpl(List<ExactSplit> exactSplits) {
        this.exactSplits = exactSplits;
    }

    @Override
    public List<UserBifurcateShare> bifurcate(Expense expense, List<User> users) {
        List<UserBifurcateShare>userBifurcateShares = new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userBifurcateShares.add(new UserBifurcateShare(exactSplits.get(i).getUser(), exactSplits.get(i).getAmount()));
        }
        return userBifurcateShares;
    }
}
