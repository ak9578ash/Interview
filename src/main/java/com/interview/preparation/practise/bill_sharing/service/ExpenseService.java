package com.interview.preparation.practise.bill_sharing.service;

import com.interview.preparation.practise.bill_sharing.exception.ExpenseNotFoundException;
import com.interview.preparation.practise.bill_sharing.model.*;
import com.interview.preparation.practise.bill_sharing.repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final NotificationService notificationService;

    public ExpenseService(ExpenseRepository expenseRepository, NotificationService notificationService) {
        this.expenseRepository = expenseRepository;
        this.notificationService = notificationService;
    }

    public Expense addExpense(Expense expense) {
        expense.setStatus(ExpenseStatus.PENDING);
        return expenseRepository.addExpense(expense);
    }

    public Expense getExpenseById(String expenseId) throws ExpenseNotFoundException {
        return expenseRepository.getExpenseById(expenseId);
    }

    public void addUserToExpense(String expenseId, User user) throws ExpenseNotFoundException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseNotFoundException("provide expenseId is invalid");
        }

        expense.getExpenseGroup().getGroupMember().add(user);
        notificationService.notifyViaEmail(user, expenseId);
        notificationService.notifyViaMessage(user, expenseId);
    }

    public void assignUserShare(String expenseId, User user, Double share) throws ExpenseNotFoundException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseNotFoundException("provide expenseId is invalid");
        }

        expense.getExpenseGroup().getUserContribution().putIfAbsent(user.getId(), new UserShare(user.getEmailId(), share, new ArrayList<>()));
    }

    public Boolean isExpenseSettled(String expenseId) throws ExpenseNotFoundException {
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            throw new ExpenseNotFoundException("provide expenseId is invalid");
        }

        Double expenseAmount = expense.getAmount();
        ExpenseGroup expenseGroup = expense.getExpenseGroup();

        for (Map.Entry<String, UserShare> entry : expenseGroup.getUserContribution().entrySet()) {
            List<Contribution> userContribution = entry.getValue().getContributions();
            for (Contribution contribution : userContribution) {
                expenseAmount = expenseAmount - contribution.getAmount();
            }
        }
        return expenseAmount <= 1;
    }
}
