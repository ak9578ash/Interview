package com.interview.preparation.practise.bill_sharing.utility.impl;

import com.interview.preparation.practise.bill_sharing.exception.ExpenseNotFoundException;
import com.interview.preparation.practise.bill_sharing.model.Expense;
import com.interview.preparation.practise.bill_sharing.model.User;
import com.interview.preparation.practise.bill_sharing.service.ExpenseService;
import com.interview.preparation.practise.bill_sharing.utility.BifurcationStrategy;
import com.interview.preparation.practise.bill_sharing.utility.model.ExactSplit;
import com.interview.preparation.practise.bill_sharing.utility.model.PercentageSplit;

import java.util.List;

public class BifurcationStrategyImpl implements BifurcationStrategy {
    private final ExpenseService expenseService;

    public BifurcationStrategyImpl(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public void bifurcateInEqual(Expense expense, List<User> userList) throws ExpenseNotFoundException {
        Double expenseAmount = expense.getAmount();
        Double individualUserExpense = expenseAmount / userList.size();
        for (User user : userList) {
            expenseService.assignUserShare(expense.getId(), user, individualUserExpense);
        }
    }

    @Override
    public void bifurcateInExact(Expense expense, List<ExactSplit> exactSplits) throws ExpenseNotFoundException {
        for (ExactSplit split : exactSplits) {
            expenseService.assignUserShare(expense.getId(), split.getUser(), split.getSplitAmount());
        }
    }

    @Override
    public void bifurcateInPercentage(Expense expense, List<PercentageSplit> percentageSplits) throws ExpenseNotFoundException {
        Double expenseAmount = expense.getAmount();
        for (PercentageSplit split : percentageSplits) {
            expenseService.assignUserShare(expense.getId(), split.getUser(), expenseAmount*(split.getPercentageSplit()/100));
        }
    }
}
