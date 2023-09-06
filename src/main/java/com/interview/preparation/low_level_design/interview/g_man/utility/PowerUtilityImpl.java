package com.interview.preparation.low_level_design.interview.g_man.utility;


import com.interview.preparation.low_level_design.interview.g_man.model.Coordinate;
import com.interview.preparation.low_level_design.interview.g_man.model.Direction;
import com.interview.preparation.low_level_design.interview.g_man.model.GMan;

public class PowerUtilityImpl implements PowerUtility {
    @Override
    public int getPowerUsedByManhattanDistance(GMan gMan, Coordinate destination) {
        int totalPowerUsed = 0;
        Direction currentDirection = gMan.getDirection();
        Coordinate currentPosition = gMan.getPosition();

        int xDiff = destination.getX() - currentPosition.getX();
        int yDiff = destination.getY() - currentPosition.getY();
        int manhattanDistance = Math.abs(xDiff) + Math.abs(yDiff);

        if (xDiff != 0 && yDiff != 0) {
            if (currentDirection.isOrthogonal(xDiff,yDiff)) {
                totalPowerUsed = UtilityConstant.TURN_COST + UtilityConstant.MOVE_COST * manhattanDistance;
            }else{
                totalPowerUsed = 2* UtilityConstant.TURN_COST + UtilityConstant.MOVE_COST * manhattanDistance;
            }
        } else if (xDiff != 0) {
            if(currentDirection.isOrthogonalX(xDiff)){
                totalPowerUsed = UtilityConstant.MOVE_COST * manhattanDistance;
            }else{
                if(currentDirection.isVertical()){
                    totalPowerUsed = UtilityConstant.TURN_COST + UtilityConstant.MOVE_COST*manhattanDistance;
                }else{
                    totalPowerUsed = 2* UtilityConstant.TURN_COST + UtilityConstant.MOVE_COST*manhattanDistance;
                }
            }
        } else if(yDiff!=0){
            if(currentDirection.isOrthogonalY(yDiff)){
                totalPowerUsed = UtilityConstant.MOVE_COST * manhattanDistance;
            }else {
                if(currentDirection.isHorizontal()){
                    totalPowerUsed = UtilityConstant.TURN_COST + UtilityConstant.MOVE_COST*manhattanDistance;
                }else{
                    totalPowerUsed = 2* UtilityConstant.TURN_COST + UtilityConstant.MOVE_COST*manhattanDistance;
                }
            }
        }
        gMan.setPosition(destination);
        gMan.setPower(gMan.getPower() - totalPowerUsed);
        return totalPowerUsed;
    }
}
