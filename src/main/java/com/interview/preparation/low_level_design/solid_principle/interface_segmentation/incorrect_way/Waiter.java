package com.interview.preparation.low_level_design.solid_principle.interface_segmentation.incorrect_way;

public class Waiter implements RestaurantEmployee{
    @Override
    public void cookFood() {
        // no body
    }

    @Override
    public void getFood() {
        // with some body
    }
}
