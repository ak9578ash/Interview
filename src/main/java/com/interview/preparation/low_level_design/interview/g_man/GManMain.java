package com.interview.preparation.low_level_design.interview.g_man;


import com.interview.preparation.low_level_design.interview.g_man.exception.InvalidCoordinateException;
import com.interview.preparation.low_level_design.interview.g_man.exception.InvalidDirectionException;
import com.interview.preparation.low_level_design.interview.g_man.model.Coordinate;
import com.interview.preparation.low_level_design.interview.g_man.model.Direction;
import com.interview.preparation.low_level_design.interview.g_man.model.GMan;
import com.interview.preparation.low_level_design.interview.g_man.utility.PowerUtility;
import com.interview.preparation.low_level_design.interview.g_man.utility.PowerUtilityImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GManMain {
    public static void main(String[] args) {
        GMan gman = null;
        PowerUtility powerUtility = new PowerUtilityImpl();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 0) {
                    continue;
                }
                String command = parts[0];
                switch (command) {
                    case "SOURCE":
                        int startX = Integer.parseInt(parts[1]);
                        int startY = Integer.parseInt(parts[2]);
                        if (startX < 0 || startX > constant.GRID_LENGTH || startY < 0 || startY > constant.GRID_HEIGHT) {
                            throw new InvalidCoordinateException("please check the coordinates");
                        }
                        if (!Direction.findByName(parts[3])) {
                            throw new InvalidDirectionException("please check the direction");
                        }
                        Direction startDirection = Direction.valueOf(parts[3]);
                        gman = new GMan(startX, startY, startDirection);
                        break;
                    case "DESTINATION":
                        int destX = Integer.parseInt(parts[1]);
                        int destY = Integer.parseInt(parts[2]);
                        if (destX < 0 || destX > constant.GRID_LENGTH || destY < 0 || destY > constant.GRID_HEIGHT) {
                            throw new InvalidCoordinateException("please check the coordinates");
                        }
                        Coordinate destination = new Coordinate(destX, destY);
                        powerUtility.getPowerUsedByManhattanDistance(gman, destination);
                        break;
                    case "PRINT_POWER":
                        System.out.println("Power: " + gman.getPower());
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCoordinateException | InvalidDirectionException e) {
            throw new RuntimeException(e);
        }
    }
}
