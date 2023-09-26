package com.interview.preparation.low_level_design.notification;

public class QrNotification extends Notification {
    public QrNotification(NotificationSender notificationSender) {
        super(notificationSender);
    }

    @Override
    void sendNotification() {
        this.notificationSender.sendNotification();
    }
}
