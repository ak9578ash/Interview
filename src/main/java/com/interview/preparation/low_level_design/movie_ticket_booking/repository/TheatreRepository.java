package com.interview.preparation.low_level_design.movie_ticket_booking.repository;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidScreenException;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidSeatException;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidTheatreException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Screen;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Seat;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Theatre;

import java.util.HashMap;
import java.util.Map;

public class TheatreRepository {
    public static Map<String , Theatre> theatreMap = new HashMap<>();
    public static Map<String , Screen> screenMap = new HashMap<>();
    public static Map<String , Seat>seatMap = new HashMap<>();

    public Theatre addTheatre(Theatre theatre){
        theatreMap.putIfAbsent(theatre.getId()  , theatre);
        return theatre;
    }

    public Screen addScreen(Screen screen){
        screenMap.putIfAbsent(screen.getId() , screen);
        return screen;
    }

    public Seat addSeat(Seat seat){
        seatMap.putIfAbsent(seat.getId(),seat);
        return seat;
    }

    public Screen addScreenInTheatre(Theatre theatre , Screen screen) throws InvalidTheatreException {
        if(theatreMap.get(theatre.getId())==null){
            throw new InvalidTheatreException("theatre not found");
        }
        theatre.addScreen(screen);
        return screen;
    }

    public Seat addSeatInScreen(Screen screen, Seat seat) throws InvalidScreenException {
        if(screenMap.get(screen.getId())==null){
            throw new InvalidScreenException("screen not found");
        }
        screen.addSeat(seat);
        return seat;
    }

    public Theatre getTheaterById(String theatreId) throws InvalidTheatreException {
        Theatre theatre = theatreMap.get(theatreId);
        if(theatre==null){
            throw new InvalidTheatreException("theatre not found");
        }
        return theatre;
    }

    public Screen getScreenById(String screenId) throws InvalidScreenException {
        Screen screen = screenMap.get(screenId);
        if(screen==null){
            throw new InvalidScreenException("screen not found");
        }
        return screen;
    }

    public Seat getSeatById(String seatId) throws InvalidSeatException {
        Seat seat = seatMap.get(seatId);
        if(seat == null){
            throw new InvalidSeatException("seat not found");
        }
        return seat;
    }



}
