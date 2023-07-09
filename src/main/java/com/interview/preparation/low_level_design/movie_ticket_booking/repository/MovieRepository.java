package com.interview.preparation.low_level_design.movie_ticket_booking.repository;

import com.interview.preparation.low_level_design.movie_ticket_booking.exception.InvalidMovieException;
import com.interview.preparation.low_level_design.movie_ticket_booking.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRepository {
    public static Map<String, Movie> movieMap = new HashMap<>();
    public static List<Movie> movieList = new ArrayList<>();

    public Movie addMovie(Movie movie) {
        movieMap.putIfAbsent(movie.getId(), movie);
        movieList.add(movie);
        return movie;
    }

    public Movie getMovieById(String movieId) throws InvalidMovieException {
        Movie movie = movieMap.get(movieId);
        if (movie == null) {
            throw new InvalidMovieException("movie not found");
        }
        return movie;
    }
}
