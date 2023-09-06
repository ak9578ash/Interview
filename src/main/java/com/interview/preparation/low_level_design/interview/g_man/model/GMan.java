package com.interview.preparation.low_level_design.interview.g_man.model;

import lombok.Setter;

public class GMan {
    @Setter
    private int power;

    private final Coordinate position;
    private final Direction direction;

    public GMan(int startX, int startY, Direction startDirection) {
        this.power = GManConstant.GMAN_POWER;
        this.position = new Coordinate(startX, startY);
        this.direction = startDirection;
    }

    public int getPower() {
        return power;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate newPosition){
        position.setX(newPosition.getX());
        position.setY(newPosition.getY());
    }
    public Direction getDirection() {
        return direction;
    }
}
