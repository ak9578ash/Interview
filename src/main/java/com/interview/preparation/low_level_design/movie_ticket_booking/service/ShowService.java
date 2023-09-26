package com.interview.preparation.low_level_design.movie_ticket_booking.service;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidShowException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;
import com.interview.preparation.low_level_design.movie_ticket_booking.repository.ShowRepository;

public class ShowService {
    private final ShowRepository showRepository;
    public ShowService(ShowRepository showRepository){
        this.showRepository = showRepository;
    }

    public Show addShow(Show show){
        return showRepository.addShow(show);
    }

    public Show getShowById(String showId) throws InvalidShowException {
        return showRepository.getShowById(showId);
    }
}
