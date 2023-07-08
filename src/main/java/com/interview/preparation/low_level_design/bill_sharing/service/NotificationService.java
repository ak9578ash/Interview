package com.interview.preparation.low_level_design.bill_sharing.service;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;

public interface NotificationService {
     void notifyUserViaMail(User user , Expense expense);
     void notifyUserViaMessage(User user ,Expense expense);
}
