package com.interview.preparation.low_level_design.top_k_priority.repository;

import java.util.Map;
import java.util.Set;

public interface TopKRepository {
  void updateUserToPriorityMap(Map<String, Integer> userToPriorityMap);

  void updatePriorityToUsersMap(Map<Integer, Set<String>> priorityToUsersMap);

  Map<Integer, Set<String>> getPriorityToUsersMap();

  Map<String, Integer> getUserToPriorityMap();
}


