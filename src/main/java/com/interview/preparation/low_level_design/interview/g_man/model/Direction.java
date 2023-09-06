package com.interview.preparation.low_level_design.interview.g_man.model;

public enum Direction {
    N, E, S, W;

    public boolean isOrthogonal(int xDiff, int yDiff) {
        if (xDiff > 0 && this == Direction.E ||
                yDiff > 0 && this == Direction.N ||
                xDiff < 0 && this == Direction.W ||
                yDiff < 0 && this == Direction.S) {
            return true;
        }
        return false;
    }

    public boolean isOrthogonalX(int xDiff) {
        if (xDiff > 0 && this == Direction.E || xDiff < 0 && this == Direction.W) {
            return true;
        }
        return false;
    }

    public boolean isOrthogonalY(int yDiff) {
        if (yDiff > 0 && this == Direction.N || yDiff < 0 && this == Direction.S) {
            return true;
        }
        return false;
    }

    public boolean isVertical() {
        return this == Direction.N || this == Direction.S;
    }

    public boolean isHorizontal() {
        return this == Direction.E || this == Direction.W;
    }

    public static boolean findByName(String name) {
        for (Direction direction : values()) {
            if (direction.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
