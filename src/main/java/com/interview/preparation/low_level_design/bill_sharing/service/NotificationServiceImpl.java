package com.interview.preparation.low_level_design.bill_sharing.service;

import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.bill_sharing.model.User;

public class NotificationServiceImpl implements NotificationService{
    @Override
    public void notifyUserViaMail(User user, Expense expense) {
        System.out.println(String.format("%s is notified through mail" , user.getName()));
    }

    @Override
    public void notifyUserViaMessage(User user, Expense expense) {
        System.out.println(String.format("%s is notified through message" , user.getName()));
    }


}
