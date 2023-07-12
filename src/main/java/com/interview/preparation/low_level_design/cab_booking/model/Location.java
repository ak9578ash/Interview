package com.interview.preparation.low_level_design.cab_booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Getter
@AllArgsConstructor
public class Location {
    private Double x;
    private Double y;

    public Double getDistance(Location toPoint) {
        return sqrt(pow(this.x - toPoint.x, 2) + pow(this.y - toPoint.y, 2));
    }
}
