package com.interview.preparation.low_level_design.interview.dining.model;

import com.interview.preparation.low_level_design.interview.dining.exception.RestaurantNotFoundException;
import com.interview.preparation.low_level_design.interview.dining.service.RestaurantService;

import java.util.List;

public class Customer extends User{
    private final RestaurantService restaurantService;

    public Customer(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> searchByName(String name) throws RestaurantNotFoundException {
        return restaurantService.searchByName(name);
    }

    public List<Restaurant> searchByCity(String cityName) throws RestaurantNotFoundException {
        return restaurantService.searchByCity(cityName);
    }

    public List<Restaurant> searchByRestaurantType(RestaurantType restaurantType) throws RestaurantNotFoundException {
        return restaurantService.searchByRestaurantType(restaurantType);
    }
}
