package com.interview.preparation.low_level_design.movie_ticket_booking.service;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidShowException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Show;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Theatre;
import com.interview.preparation.low_level_design.movie_ticket_booking.repository.ShowRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Theatre, List<Show>> getShowGroupByTheatre() {
        List<Show> showList = showRepository.getAllShows();
        Map<Theatre, List<Show>> theatreToShowMap = new HashMap<>();

        for(int i=0;i<showList.size();i++){
            Show show = showList.get(i);
            if(theatreToShowMap.containsKey(show.getTheatre())){
                theatreToShowMap.get(show.getTheatre()).add(show);
            }else{
                List<Show>shows = new ArrayList<>();
                shows.add(show);
                theatreToShowMap.put(show.getTheatre() , shows);
            }
        }
        return theatreToShowMap;
    }
}
