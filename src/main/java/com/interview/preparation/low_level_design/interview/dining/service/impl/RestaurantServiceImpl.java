package com.interview.preparation.low_level_design.interview.dining.service.impl;

import com.interview.preparation.low_level_design.interview.dining.exception.RestaurantNotFoundException;
import com.interview.preparation.low_level_design.interview.dining.model.Restaurant;
import com.interview.preparation.low_level_design.interview.dining.model.RestaurantType;
import com.interview.preparation.low_level_design.interview.dining.repository.RestaurantRepository;
import com.interview.preparation.low_level_design.interview.dining.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant registerRestaurant(Restaurant restaurant) {
        return restaurantRepository.registerRestaurant(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        return restaurantRepository.updateRestaurant(restaurant);
    }

    @Override
    public Restaurant deleteRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        return restaurantRepository.deleteRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> searchByName(String restaurantName) throws RestaurantNotFoundException {
        return restaurantRepository.searchByName(restaurantName);
    }

    @Override
    public List<Restaurant> searchByCity(String cityName) throws RestaurantNotFoundException {
       return restaurantRepository.searchByCity(cityName);
    }

    @Override
    public List<Restaurant> searchByRestaurantType(RestaurantType restaurantType) throws RestaurantNotFoundException {
        return restaurantRepository.searchByRestaurantType(restaurantType);
    }
}
