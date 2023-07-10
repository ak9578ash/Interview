package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private Theatre theatre;
    private LocalDateTime showTime;
    private Integer showDuration;

    public Show(Movie movie, Screen screen , Theatre theatre , LocalDateTime showTime,Integer showDuration){
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.screen = screen;
        this.theatre = theatre;
        this.showTime = showTime;
        this.showDuration = showDuration;
    }

}
