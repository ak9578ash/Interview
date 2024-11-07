package com.interview.preparation.low_level_design.messaging_app.service.impl;

import com.interview.preparation.low_level_design.messaging_app.models.User;
import com.interview.preparation.low_level_design.messaging_app.service.NotificationObserver;

public class InAppNotificationOberserverImpl implements NotificationObserver {
    @Override
    public void notify(User user) {
        System.out.printf("you got a message from  user %s via inApp notification",user.getProfile().getFirstName());

    }
}
