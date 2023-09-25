package com.interview.preparation.practise.bill_sharing.service;

import com.interview.preparation.practise.bill_sharing.exception.ExpenseNotFoundException;
import com.interview.preparation.practise.bill_sharing.exception.ExpenseSettledException;
import com.interview.preparation.practise.bill_sharing.model.Contribution;
import com.interview.preparation.practise.bill_sharing.model.Expense;
import com.interview.preparation.practise.bill_sharing.model.ExpenseStatus;
import com.interview.preparation.practise.bill_sharing.model.User;
import com.interview.preparation.practise.bill_sharing.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final ExpenseService expenseService;

    public UserService(UserRepository userRepository, ExpenseService expenseService) {
        this.userRepository = userRepository;
        this.expenseService = expenseService;
    }
    
    public User adduser(User user){
        return userRepository.addUser(user);
    }
    
    public void contributeToExpense(String expenseId , User user , Contribution contribution) throws ExpenseNotFoundException, ExpenseSettledException {
        Expense expense = expenseService.getExpenseById(expenseId);
        if(expense==null){
            throw new ExpenseNotFoundException("provided expenseId is invalid");
        }

        if(expense.getStatus()== ExpenseStatus.SETTLED){
            throw new ExpenseSettledException("provide expense is already settled");
        }

        expense.getExpenseGroup().getUserContribution().get(user.getEmailId()).getContributions().add(contribution);
    }
}
