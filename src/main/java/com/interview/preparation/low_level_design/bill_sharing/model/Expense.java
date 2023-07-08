package com.interview.preparation.low_level_design.bill_sharing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Expense {
    private String id;
    private String userId;
    private String description;
    private LocalDateTime expenseDate;
    private Double expenseAmount;
    private ExpenseStatus expenseStatus;
    private ExpenseGroup expenseGroup;
}
