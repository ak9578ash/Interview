package com.interview.preparation.low_level_design.movie_ticket_booking.service.observer;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsNotificationObserver implements NotificationObserver{
  @Override
  public void notifyObserver(User user) {
    log.info("Sending SMS to user {}", user.getUserProfile().getFirstName());
  }
}
