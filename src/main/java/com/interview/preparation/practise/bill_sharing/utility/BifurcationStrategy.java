package com.interview.preparation.practise.bill_sharing.utility;

import com.interview.preparation.practise.bill_sharing.exception.ExpenseNotFoundException;
import com.interview.preparation.practise.bill_sharing.model.Expense;
import com.interview.preparation.practise.bill_sharing.model.User;
import com.interview.preparation.practise.bill_sharing.utility.model.ExactSplit;
import com.interview.preparation.practise.bill_sharing.utility.model.PercentageSplit;

import java.util.List;

public interface BifurcationStrategy {
    void bifurcateInEqual(Expense expense , List<User> userList) throws ExpenseNotFoundException;
    void bifurcateInExact(Expense expense , List<ExactSplit>exactSplits) throws ExpenseNotFoundException;
    void bifurcateInPercentage(Expense expense , List<PercentageSplit>percentageSplits) throws ExpenseNotFoundException;
}
