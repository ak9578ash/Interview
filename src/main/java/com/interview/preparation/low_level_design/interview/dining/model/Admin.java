package com.interview.preparation.low_level_design.interview.dining.model;

import com.interview.preparation.low_level_design.interview.dining.exception.RestaurantNotFoundException;
import com.interview.preparation.low_level_design.interview.dining.service.RestaurantService;

public class Admin extends User{
    private final RestaurantService restaurantService;

    public Admin(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Restaurant registerRestaurant(Restaurant restaurant){
        return restaurantService.registerRestaurant(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        return restaurantService.updateRestaurant(restaurant);
    }

    public Restaurant deleteRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        return restaurantService.deleteRestaurant(restaurant);
    }
}
