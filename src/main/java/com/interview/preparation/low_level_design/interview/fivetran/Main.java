package com.interview.preparation.low_level_design.interview.fivetran;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> queries = Arrays.asList("generate token1 100", "generate token2 110", "count 105", "renew token1 120", "count 115");
        List<Integer> result = new Solution1().getUnexpiredTokens(10, queries);
        System.out.println(result);
    }
}
