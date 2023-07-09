package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private LocalDateTime showTime;
    private Integer showDuration;

}
