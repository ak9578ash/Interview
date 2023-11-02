package com.interview.preparation.low_level_design.interview.dining;

import com.interview.preparation.low_level_design.interview.dining.repository.RestaurantRepository;
import com.interview.preparation.low_level_design.interview.dining.repository.TableBookingRepository;
import com.interview.preparation.low_level_design.interview.dining.repository.impl.RestaurantRepositoryImpl;
import com.interview.preparation.low_level_design.interview.dining.repository.impl.TableBookingRespositoryImpl;
import com.interview.preparation.low_level_design.interview.dining.service.RestaurantService;
import com.interview.preparation.low_level_design.interview.dining.service.TableBookingService;
import com.interview.preparation.low_level_design.interview.dining.service.impl.RestaurantServiceImpl;
import com.interview.preparation.low_level_design.interview.dining.service.impl.TableBookingServiceImpl;

public class DiningMain {
    public static RestaurantRepository restaurantRepository;
    public static RestaurantService restaurantService;
    public static TableBookingRepository tableBookingRepository;
    public static TableBookingService tableBookingService;

    public static void main(String[] args) {
        restaurantRepository = new RestaurantRepositoryImpl();
        restaurantService = new RestaurantServiceImpl(restaurantRepository);

        tableBookingRepository = new TableBookingRespositoryImpl();
        tableBookingService = new TableBookingServiceImpl(tableBookingRepository);


    }
}
