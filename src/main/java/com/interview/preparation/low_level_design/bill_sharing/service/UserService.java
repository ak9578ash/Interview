package com.interview.preparation.low_level_design.bill_sharing.service;

import com.interview.preparation.low_level_design.bill_sharing.exception.ContributionExceededException;
import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseDoesNotExistsException;
import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseSettledException;
import com.interview.preparation.low_level_design.bill_sharing.exception.InvalidExpenseStateException;
import com.interview.preparation.low_level_design.bill_sharing.model.*;
import com.interview.preparation.low_level_design.bill_sharing.repository.ExpenseRepository;
import com.interview.preparation.low_level_design.bill_sharing.repository.UserRepository;
import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;

public class UserService {
    private final UserRepository userRepository;
    private final ExpenseService expenseService;
    public UserService(UserRepository userRepository , ExpenseService expenseService){
        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }
    public User addUser(String emailId, String name, String phoneNumber) {
        User user = new User(name, emailId, phoneNumber);
        userRepository.addUser(user);
        return user;
    }

    public void contributeToExpense(String expenseId, String emailId, Contribution contribution)
            throws InvalidExpenseStateException, ExpenseSettledException, ContributionExceededException, ExpenseDoesNotExistsException {
        Expense expense = expenseService.getExpenseById(expenseId);
        if(expense == null){
            throw new InvalidExpenseStateException("expense does not exist");
        }
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
                    String.format("User %s contribution %f exceeded the share %f",
                            emailId, contribution.getContributionValue(), userShare.getShare()));
        }
        userShare.getContributions().add(contribution);
    }

    public void contributeToExpense(String expenseId, String fromEmailId, String toEmailId, Contribution contribution)
            throws InvalidExpenseStateException, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        if(expense == null){
            throw new InvalidExpenseStateException("expense does not exist");
        }
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
            throw new InvalidExpenseStateException("Invalid expense State");
        }
        if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
            throw new ExpenseSettledException("Expense is already settled.");
        }
        UserShare fromUserShare = expenseGroup.getUserContributions().get(fromEmailId);
        UserShare destUserShare = expenseGroup.getUserContributions().get(toEmailId);
        if (contribution.getContributionValue() > destUserShare.getShare()) {
            throw new ContributionExceededException(String.format("User %s contribution %f exceeded the share %f of %s",
                    fromEmailId, contribution.getContributionValue(), destUserShare.getShare(), toEmailId));
        }

        fromUserShare.getContributions().add(contribution);
    }
}
