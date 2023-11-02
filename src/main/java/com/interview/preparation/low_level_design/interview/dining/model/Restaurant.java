package com.interview.preparation.low_level_design.interview.dining.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Restaurant {
    private String id;
    private String name;
    private Address address;
    private RestaurantType restaurantType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Restaurant(String name , Address address , LocalDateTime startTime , LocalDateTime endTime){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
