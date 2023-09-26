package com.interview.preparation.low_level_design.notification;

public class TextNotification extends Notification {
    public TextNotification(NotificationSender notificationSender) {
        super(notificationSender);
    }

    @Override
    void sendNotification() {
        System.out.println("sending text notification");
        this.notificationSender.sendNotification();
    }
}
