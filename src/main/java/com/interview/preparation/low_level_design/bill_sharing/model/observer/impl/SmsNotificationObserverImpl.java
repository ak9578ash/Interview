package com.interview.preparation.low_level_design.bill_sharing.model.observer.impl;

import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.model.observer.NotificationObserver;

public class SmsNotificationObserverImpl implements NotificationObserver {
    @Override
    public void notifyObserver(User user) {
        System.out.println(user.getName() + " is notified vai SMS");
    }
}
