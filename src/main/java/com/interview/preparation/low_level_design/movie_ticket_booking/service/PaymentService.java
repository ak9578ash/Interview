package com.interview.preparation.low_level_design.movie_ticket_booking.service;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.BadRequestException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Booking;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Payment;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import com.interview.preparation.low_level_design.movie_ticket_booking.repository.PaymentRepository;
import com.interview.preparation.low_level_design.movie_ticket_booking.utils.SeatLockProvider;

public class PaymentService {
  private final PaymentRepository paymentRepository;
  private final Integer allowedRetries;
  private final SeatLockProvider seatLockProvider;

  public PaymentService(PaymentRepository paymentRepository, Integer allowedRetries,
                        SeatLockProvider seatLockProvider) {
    this.paymentRepository = paymentRepository;
    this.allowedRetries = allowedRetries;
    this.seatLockProvider = seatLockProvider;
  }

  public Payment makePayment(Booking booking, Payment payment) {
    return paymentRepository.makePayment(booking, payment);
  }

  public Payment getPaymentByBookingId(String bookingId) {
    return paymentRepository.getPaymentByBookingId(bookingId);
  }

  public void addToFailureBooking(Booking booking) {
    paymentRepository.addToFailureBooking(booking);
  }

  public void processPaymentFailed(Booking booking, User user) throws BadRequestException {
    if (!booking.getUser().equals(user)) {
      throw new BadRequestException();
    }

    Integer failuresCount = paymentRepository.getBookingFailureCount(booking);
    if (failuresCount > allowedRetries) {
      seatLockProvider.unlockSeats(booking.getShow(), booking.getBookedSeats(), booking.getUser().getId());
    }
  }
}
