package com.interview.preparation.low_level_design.movie_ticket_booking.service.observer;


import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;

public interface NotificationObserver {
  void notifyObserver(User user);
}
