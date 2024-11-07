package com.interview.preparation.low_level_design.messaging_app.service;

import com.interview.preparation.low_level_design.messaging_app.models.User;

public interface NotificationObserver {
    void notify(User user);
}
