package com.interview.preparation.low_level_design.notification;

public abstract class Notification {
     NotificationSender notificationSender;

    public Notification(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    abstract void sendNotification();
}
