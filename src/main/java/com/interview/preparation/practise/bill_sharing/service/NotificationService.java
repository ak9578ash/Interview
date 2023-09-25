package com.interview.preparation.practise.bill_sharing.service;

import com.interview.preparation.practise.bill_sharing.model.User;

public interface NotificationService {
    void notifyViaEmail(User user , String message);
    void notifyViaMessage(User user , String message);
}
