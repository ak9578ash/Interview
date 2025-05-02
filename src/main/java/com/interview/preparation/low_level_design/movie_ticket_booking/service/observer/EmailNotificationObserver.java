package com.interview.preparation.low_level_design.movie_ticket_booking.service.observer;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotificationObserver implements NotificationObserver{
  @Override
  public void notifyObserver(User user) {
    log.info("Sending Email to user {}", user.getUserProfile().getFirstName());
  }
}
