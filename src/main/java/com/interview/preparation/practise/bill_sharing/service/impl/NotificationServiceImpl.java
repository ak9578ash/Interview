package com.interview.preparation.practise.bill_sharing.service.impl;

import com.interview.preparation.practise.bill_sharing.model.User;
import com.interview.preparation.practise.bill_sharing.service.NotificationService;

public class NotificationServiceImpl implements NotificationService{
    @Override
    public void notifyViaEmail(User user, String message) {
        System.out.printf("%s is added to %s and notified via email",user.getFirstName() , message);
    }

    @Override
    public void notifyViaMessage(User user, String message) {
        System.out.printf("%s is added to %s and notified via message",user.getFirstName() , message);
    }
}
