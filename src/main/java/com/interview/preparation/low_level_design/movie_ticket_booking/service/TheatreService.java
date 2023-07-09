package com.interview.preparation.low_level_design.movie_ticket_booking.service;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidScreenException;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidSeatException;
import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidTheatreException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Screen;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Seat;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Theatre;
import com.interview.preparation.low_level_design.movie_ticket_booking.repository.TheatreRepository;

public class TheatreService {
    private TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre addTheatre(Theatre theatre){
        return theatreRepository.addTheatre(theatre);
    }

    public Screen addScreen(Screen screen){
        return theatreRepository.addScreen(screen);
    }

    public Seat addSeat(Seat seat){
        return theatreRepository.addSeat(seat);
    }

    public Screen addScreenInTheatre(Theatre theatre , Screen screen) throws InvalidTheatreException {
        return theatreRepository.addScreenInTheatre(theatre,screen);
    }

    public Seat addSeatInScreen(Screen screen, Seat seat) throws InvalidScreenException{
        return theatreRepository.addSeatInScreen(screen,seat);
    }

    public Theatre getTheaterById(String theatreId) throws InvalidTheatreException {
        return theatreRepository.getTheaterById(theatreId);
    }

    public Screen getScreenById(String screenId) throws InvalidScreenException {
        return theatreRepository.getScreenById(screenId);
    }

    public Seat getSeatById(String seatId) throws InvalidSeatException {
        return theatreRepository.getSeatById(seatId);
    }
}
