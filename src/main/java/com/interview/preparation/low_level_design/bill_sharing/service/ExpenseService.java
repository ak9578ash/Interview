package com.interview.preparation.low_level_design.bill_sharing.service;

import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseDoesNotExistsException;
import com.interview.preparation.low_level_design.bill_sharing.model.*;
import com.interview.preparation.low_level_design.bill_sharing.repository.ExpenseRepository;
import com.interview.preparation.low_level_design.bill_sharing.repository.UserRepository;
import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class ExpenseService {
    private final NotificationService notificationService;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository, NotificationService notificationService) {
        this.expenseRepository = expenseRepository;
        this.notificationService = notificationService;
    }

    public Expense createExpense(String description, LocalDateTime expenseDate, double expenseAmount, String userId) {
        Expense expense = Expense.builder()
                .id(UUID.randomUUID().toString())
                .description(description)
                .expenseDate(expenseDate)
                .expenseAmount(expenseAmount)
                .userId(userId)
                .expenseStatus(ExpenseStatus.CREATED)
                .expenseGroup(new ExpenseGroup())
                .build();
        expenseRepository.addExpense(expense);
        return expense;
    }

    public void addUsersToExpense(String expenseId, User user) throws ExpenseDoesNotExistsException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseDoesNotExistsException("Better create expense and come here....");
        }
        expense.getExpenseGroup().getGroupMembers().add(user);

        notificationService.notifyUserViaMail(user, expense);
        notificationService.notifyUserViaMessage(user, expense);
    }

    public void assignExpenseShare(String expenseId, User user, double share) throws ExpenseDoesNotExistsException, BadRequestException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseDoesNotExistsException("Better create expense and come here....");
        }
        expense.getExpenseGroup()
                .getUserContributions()
                .putIfAbsent(user.getEmailId(), new UserShare(user.getEmailId(), share));
    }

    public void setExpenseStatus(String expenseId, ExpenseStatus expenseStatus) throws ExpenseDoesNotExistsException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseDoesNotExistsException("Better create expense and come here....");
        }
        expense.setExpenseStatus(expenseStatus);
    }

    public boolean isExpenseSettled(String expenseId) throws ExpenseDoesNotExistsException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseDoesNotExistsException("Better create expense and come here....");
        }
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        Map<String, UserShare> userContributions = expenseGroup.getUserContributions();

        double total = expense.getExpenseAmount();

        for (Map.Entry<String, UserShare> entry : userContributions.entrySet()) {
            UserShare userShare = entry.getValue();
            for (Contribution contribution : userShare.getContributions()) {
                total -= contribution.getContributionValue();
            }
        }
        return total <= 1;
    }

    public Expense getExpenseById(String expenseId) throws ExpenseDoesNotExistsException {
        return expenseRepository.getExpenseById(expenseId);
    }

    public Expense addExpense(Expense expense){
        return expenseRepository.addExpense(expense);
    }
}

