package com.interview.preparation.low_level_design.bill_sharing.utils;

import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseDoesNotExistsException;
import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.ExactSplit;
import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.service.ExpenseService;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.PercentageSplit;
import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;

import java.util.List;

public class SplittingStrategyImpl implements SplittingStrategy {
    private final ExpenseService expenseService;

    public SplittingStrategyImpl(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public void bifurcateInEqual(Expense expense, List<User> userList) throws ExpenseDoesNotExistsException, BadRequestException {
        Double totalExpenseAmount = expense.getExpenseAmount();
        Double individualExpenseShare = totalExpenseAmount / userList.size();

        for (User user : userList) {
            expenseService.assignExpenseShare(expense.getId(), user, individualExpenseShare);
        }
    }

    @Override
    public void bifurcateInExact(Expense expense, List<ExactSplit> amountList) throws ExpenseDoesNotExistsException, BadRequestException {
        for (ExactSplit split : amountList) {
            expenseService.assignExpenseShare(expense.getId(), split.getUser(), split.getAmount());
        }
    }

    @Override
    public void bifurcateInPercentage(Expense expense, List<PercentageSplit> amountList) throws ExpenseDoesNotExistsException, BadRequestException {
        for (PercentageSplit split : amountList) {
            Double userShare = (split.getPercentage() / 100) * split.getExpense().getExpenseAmount();
            expenseService.assignExpenseShare(split.getExpense().getId(), split.getUser(), userShare);
        }
    }
}
