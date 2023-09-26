package com.interview.preparation.low_level_design.notification.impl;

import com.interview.preparation.low_level_design.notification.NotificationSender;

public class EmailNotificationSenderImpl implements NotificationSender {
    @Override
    public void sendNotification() {
        System.out.println("notification send via email");
    }
}
