package com.interview.preparation.low_level_design.movie_ticket_booking.utils;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.SeatTemporarilyUnavailableException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Seat;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.SeatLock;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider {
    private final Map<Show, Map<Seat, SeatLock>> locks;
    private final Integer lockoutTime;

    public InMemorySeatLockProvider(Integer lockoutTime) {
        this.locks = new HashMap<>();
        this.lockoutTime = lockoutTime;
    }

    @Override
    public synchronized void lockSeats(Show show, List<Seat> seats, String user) throws SeatTemporarilyUnavailableException {
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat)) {
                throw new SeatTemporarilyUnavailableException("selected seat is not available at the moment");
            }
        }

        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockoutTime);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat : seats){
            if(validateLock(show,seat,user)){
                unlockSeat(show,seat);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String userId) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(userId);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if (!locks.containsKey(show)) {
            return new ArrayList<>();
        }
        final List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }
}
