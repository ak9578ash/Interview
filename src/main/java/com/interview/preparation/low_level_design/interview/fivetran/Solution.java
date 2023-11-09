package com.interview.preparation.low_level_design.interview.fivetran;

import java.util.*;

public class Solution {
    public List<Integer> getUnexpiredTokens(int timeToLive, List<String> queries) {
        Map<String, Integer> tokenMap = new HashMap<>();
        PriorityQueue<Integer> expirationTimes = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for (String query : queries) {
            String[] q = query.split(" ");
            String operation = q[0];

            switch (operation) {
                case "count":
                    int currentTime = Integer.parseInt(q[1]);
                    while (!expirationTimes.isEmpty() && expirationTimes.peek() <= currentTime) {
                        expirationTimes.poll();
                    }
                    result.add(expirationTimes.size());
                    break;

                case "generate":
                    String id = q[1];
                    int currentTime1 = Integer.parseInt(q[2]);
                    int expirationTime = currentTime1 + timeToLive;
                    tokenMap.put(id, expirationTime);
                    expirationTimes.offer(expirationTime);
                    break;

                case "renew":
                    String idToRenew = q[1];
                    int currentTime2 = Integer.parseInt(q[2]);
                    if (tokenMap.containsKey(idToRenew)) {
                        int existingExpirationTime = tokenMap.get(idToRenew);
                        if (currentTime2 < existingExpirationTime) {
                            expirationTimes.remove(existingExpirationTime);
                            int newExpirationTime = currentTime2 + timeToLive;
                            tokenMap.put(idToRenew, newExpirationTime);
                            expirationTimes.offer(newExpirationTime);
                        }
                    }
                    break;
            }
        }
        return result;
    }
}
