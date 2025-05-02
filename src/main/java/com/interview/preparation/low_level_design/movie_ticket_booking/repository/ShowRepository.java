package com.interview.preparation.low_level_design.movie_ticket_booking.repository;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidShowException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRepository {
    public static Map<String , Show> showMap = new HashMap<>();
    public static List<Show> showList = new ArrayList<>();

    public Show addShow(Show show){
        showMap.putIfAbsent(show.getId() , show);
        showList.add(show);
        return show;
    }

    public Show getShowById(String showId) throws InvalidShowException {
        Show show = showMap.get(showId);
        if(show == null){
            throw new InvalidShowException("show not found");
        }
        return show;
    }

    public List<Show> getAllShows() {
        return showList;
    }
}
