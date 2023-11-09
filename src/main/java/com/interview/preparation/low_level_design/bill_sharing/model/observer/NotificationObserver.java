package com.interview.preparation.low_level_design.bill_sharing.model.observer;

import com.interview.preparation.low_level_design.bill_sharing.model.User;

public interface NotificationObserver {
    void notifyObserver(User user);
}
