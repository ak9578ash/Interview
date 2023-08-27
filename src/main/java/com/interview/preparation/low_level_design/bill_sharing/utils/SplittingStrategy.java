package com.interview.preparation.low_level_design.bill_sharing.utils;

import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseDoesNotExistsException;
import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.ExactSplit;
import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.PercentageSplit;
import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;

import java.util.List;

public interface SplittingStrategy {
    void bifurcateInEqual(Expense expense , List<User> userList) throws ExpenseDoesNotExistsException, BadRequestException;
    void bifurcateInExact(Expense expense , List<ExactSplit>amountList) throws ExpenseDoesNotExistsException, BadRequestException;
    void bifurcateInPercentage(Expense expense , List<PercentageSplit>amountList) throws ExpenseDoesNotExistsException, BadRequestException;
}
