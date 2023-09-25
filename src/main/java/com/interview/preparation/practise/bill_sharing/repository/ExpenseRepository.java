package com.interview.preparation.practise.bill_sharing.repository;

import com.interview.preparation.practise.bill_sharing.exception.ExpenseNotFoundException;
import com.interview.preparation.practise.bill_sharing.model.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepository {
    private final Map<String  , Expense> expenseMap = new HashMap<>();
    private final List<Expense> expenseList = new ArrayList<>();

    public Expense addExpense(Expense expense){
        expenseMap.putIfAbsent(expense.getId() , expense);
        expenseList.add(expense);
        return expense;
    }

    public Expense getExpenseById(String expenseId) throws ExpenseNotFoundException {
        if(!expenseMap.containsKey(expenseId)){
            throw new ExpenseNotFoundException("provided expense does not exist");
        }
        return expenseMap.get(expenseId);
    }
}
