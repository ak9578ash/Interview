package com.interview.preparation.low_level_design.interview.dining.service;

import com.interview.preparation.low_level_design.interview.dining.exception.RestaurantNotFoundException;
import com.interview.preparation.low_level_design.interview.dining.model.Restaurant;
import com.interview.preparation.low_level_design.interview.dining.model.RestaurantType;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantService {
    Restaurant registerRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
    Restaurant deleteRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
    List<Restaurant> searchByName(String restaurantName) throws RestaurantNotFoundException;
    List<Restaurant> searchByCity(String cityName) throws RestaurantNotFoundException;
    List<Restaurant> searchByRestaurantType(RestaurantType restaurantType) throws RestaurantNotFoundException;
}
