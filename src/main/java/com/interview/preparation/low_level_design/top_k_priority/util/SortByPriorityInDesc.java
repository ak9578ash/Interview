package com.interview.preparation.low_level_design.top_k_priority.util;

import java.util.Comparator;

public class SortByPriorityInDesc implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}
