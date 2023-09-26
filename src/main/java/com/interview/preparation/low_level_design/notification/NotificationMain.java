package com.interview.preparation.low_level_design.notification;

import com.interview.preparation.low_level_design.notification.impl.EmailNotificationSenderImpl;
import com.interview.preparation.low_level_design.notification.impl.SmsNotificationSenderImpl;

public class NotificationMain {
    public static void main(String[] args) {
        TextNotification textNotification = new TextNotification(new EmailNotificationSenderImpl());
        textNotification.sendNotification();
    }
}
