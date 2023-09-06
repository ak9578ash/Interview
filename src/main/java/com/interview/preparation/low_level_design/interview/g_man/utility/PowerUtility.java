package com.interview.preparation.low_level_design.interview.g_man.utility;

import com.interview.preparation.low_level_design.interview.g_man.model.Coordinate;
import com.interview.preparation.low_level_design.interview.g_man.model.GMan;

public interface PowerUtility {
    int getPowerUsedByManhattanDistance(GMan gMan , Coordinate destination);
}
