package com.interview.preparation.low_level_design.interview.dining.repository.impl;

import com.interview.preparation.low_level_design.interview.dining.exception.RestaurantNotFoundException;
import com.interview.preparation.low_level_design.interview.dining.model.Restaurant;
import com.interview.preparation.low_level_design.interview.dining.model.RestaurantType;
import com.interview.preparation.low_level_design.interview.dining.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    private static List<Restaurant> restaurantsList = new ArrayList<>();
    @Override
    public Restaurant registerRestaurant(Restaurant restaurant) {
        restaurantsList.add(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        for(int i=0;i<restaurantsList.size();i++){
            if(restaurantsList.get(i).getId().equals(restaurant.getId())){
                restaurantsList.remove(restaurant);
                restaurantsList.add(restaurant);
                return restaurant;
            }
        }
        throw new RestaurantNotFoundException("provided restaurant does not exsist");
    }

    @Override
    public Restaurant deleteRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        for(int i=0;i<restaurantsList.size();i++){
            if(restaurantsList.get(i).getId().equals(restaurant.getId())){
                restaurantsList.remove(restaurant);
                return restaurant;
            }
        }
        throw new RestaurantNotFoundException("provided restaurant does not exist");
    }

    @Override
    public List<Restaurant> searchByName(String restaurantName) throws RestaurantNotFoundException {
        List<Restaurant>fetchedRestaurantList = new ArrayList<>();
        for(int i=0;i<restaurantsList.size();i++){
            if(restaurantsList.get(i).getName().equals(restaurantName)){
                fetchedRestaurantList.add(restaurantsList.get(i));
            }
        }
        if(fetchedRestaurantList.isEmpty()){
            throw new RestaurantNotFoundException("restaurant with given name does not exist");
        }
        return fetchedRestaurantList;
    }

    @Override
    public List<Restaurant> searchByCity(String cityName) throws RestaurantNotFoundException {
        List<Restaurant>fetchedRestaurantList = new ArrayList<>();
        for(int i=0;i<restaurantsList.size();i++){
            if(restaurantsList.get(i).getAddress().getCity().equals(cityName)){
                fetchedRestaurantList.add(restaurantsList.get(i));
            }
        }
        if(fetchedRestaurantList.isEmpty()){
            throw new RestaurantNotFoundException("restaurant with given city name does not exist");
        }
        return fetchedRestaurantList;
    }

    @Override
    public List<Restaurant> searchByRestaurantType(RestaurantType restaurantType) throws RestaurantNotFoundException {
        List<Restaurant>fetchedRestaurantList = new ArrayList<>();
        for(int i=0;i<restaurantsList.size();i++){
            if(restaurantsList.get(i).getRestaurantType().equals(restaurantType)){
                fetchedRestaurantList.add(restaurantsList.get(i));
            }
        }
        if(fetchedRestaurantList.isEmpty()){
            throw new RestaurantNotFoundException("restaurant with given type does not exist");
        }
        return fetchedRestaurantList;
    }
}
