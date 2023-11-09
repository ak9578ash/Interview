package com.interview.preparation.low_level_design.bill_sharing.service;

import com.interview.preparation.low_level_design.bill_sharing.model.User;
import com.interview.preparation.low_level_design.bill_sharing.model.observer.NotificationObserver;

public interface ExpenseServiceSubject {
    void addObserver(NotificationObserver observer);
    void removeObserver(NotificationObserver observer);
    void notifyAllObserver(User user);
}
