package com.interview.preparation.low_level_design.bill_sharing.model.observer.impl;

import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.model.observer.NotificationObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailNotificationObserverImpl implements NotificationObserver {
    @Override
    public void notifyObserver(User user) {
        log.info(user.getName() + " is notified via E-mail");
    }
}
