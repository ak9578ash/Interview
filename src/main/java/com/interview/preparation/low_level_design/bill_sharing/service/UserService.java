package com.interview.preparation.low_level_design.bill_sharing.service;

import com.interview.preparation.low_level_design.bill_sharing.exception.ContributionExceededException;
import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseSettledException;
import com.interview.preparation.low_level_design.bill_sharing.exception.InvalidExpenseStateException;
import com.interview.preparation.low_level_design.bill_sharing.model.*;
import com.interview.preparation.low_level_design.bill_sharing.repository.ExpenseRepository;
import com.interview.preparation.low_level_design.bill_sharing.repository.UserRepository;

public class UserService {

    public User createUser(String emailId, String name, String phoneNumber) {
        User user = new User(name, emailId, phoneNumber);
        UserRepository.userHashMap.putIfAbsent(emailId, user);
        return user;
    }

    public void contributeToExpense(String expenseId, String emailId, Contribution contribution)
            throws InvalidExpenseStateException, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
            throw new InvalidExpenseStateException("Invalid expense State");
        }
        if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
            throw new ExpenseSettledException("Expense is already settled.");
        }
        UserShare userShare = expenseGroup.getUserContributions().get(emailId);
        if (contribution.getContributionValue() > userShare.getShare()) {
            throw new ContributionExceededException(
                    String.format("User %s contribution %d exceeded the share %d",
                            emailId, contribution.getContributionValue(), userShare.getShare()));
        }
        userShare.getContributions().add(contribution);
    }

    public void contributeToExpense(String expenseId, String fromEmailId, String toEmailId, Contribution contribution) throws InvalidExpenseStateException, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
            throw new InvalidExpenseStateException("Invalid expense State");
        }
        if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
            throw new ExpenseSettledException("Expense is already settled.");
        }
        UserShare fromUserShar = expenseGroup.getUserContributions().get(fromEmailId);
        UserShare destUserShare = expenseGroup.getUserContributions().get(toEmailId);
        if (contribution.getContributionValue() > destUserShare.getShare()) {
            throw new ContributionExceededException(String.format("User %s contribution %f exceeded the share %f of %s",
                    fromEmailId, contribution.getContributionValue(), destUserShare.getShare(), toEmailId));
        }

        fromUserShar.getContributions().add(contribution);
    }
}
