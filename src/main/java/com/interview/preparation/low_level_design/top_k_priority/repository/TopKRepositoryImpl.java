package com.interview.preparation.low_level_design.top_k_priority.repository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TopKRepositoryImpl implements TopKRepository {
  private Map<String, Integer> userToPriorityMap;
  private Map<Integer, Set<String>> priorityToUsersMap;
  private final Comparator<Integer> sortByComparator;
  public TopKRepositoryImpl(Comparator<Integer> sortByComparator) {
    this.sortByComparator = sortByComparator;
    this.userToPriorityMap = new LinkedHashMap<>();
    this.priorityToUsersMap = new TreeMap<>(sortByComparator);
  }

  @Override
  public void updateUserToPriorityMap(Map<String, Integer> userToPriorityMap) {
    this.userToPriorityMap = new LinkedHashMap<>(userToPriorityMap);
  }

  @Override
  public void updatePriorityToUsersMap(Map<Integer, Set<String>> priorityToUsersMap) {
    this.priorityToUsersMap = new TreeMap<>(sortByComparator);
    this.priorityToUsersMap.putAll(priorityToUsersMap);
  }

  @Override
  public Map<Integer, Set<String>> getPriorityToUsersMap() {
    return this.priorityToUsersMap;
  }

  @Override
  public Map<String, Integer> getUserToPriorityMap() {
    return this.userToPriorityMap;
  }

}
