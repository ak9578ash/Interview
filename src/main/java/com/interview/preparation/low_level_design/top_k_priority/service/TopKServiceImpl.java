package com.interview.preparation.low_level_design.top_k_priority.service;

import com.interview.preparation.low_level_design.top_k_priority.repository.TopKRepository;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TopKServiceImpl implements TopKService {
  private final TopKRepository topKRepository;
  private int temp = 0;

  @Override
  public void updateUserPriority(String userId, int priority) {
    temp++;
    Map<Integer, Set<String>> priorityToUsersMap = topKRepository.getPriorityToUsersMap();
    Map<String, Integer> userToPriorityMap = topKRepository.getUserToPriorityMap();

    int prevPriority = 0;
    if (userToPriorityMap.containsKey(userId)) {
      prevPriority = userToPriorityMap.get(userId);
    }

    if (priorityToUsersMap.containsKey(prevPriority)) {
      if (priorityToUsersMap.get(prevPriority).contains(userId)) {
        priorityToUsersMap.get(prevPriority).remove(userId);
        if (priorityToUsersMap.get(prevPriority).isEmpty()) {
          priorityToUsersMap.remove(prevPriority);
        }
      }
    }

    int newPriority = prevPriority + priority;
    userToPriorityMap.put(userId, newPriority);

    if (priorityToUsersMap.containsKey(newPriority)) {
      priorityToUsersMap.get(newPriority).add(userId);
    } else {
      Set<String> users = new LinkedHashSet<>();
      users.add(userId);
      priorityToUsersMap.put(newPriority, users);
    }

    topKRepository.updateUserToPriorityMap(userToPriorityMap);
    topKRepository.updatePriorityToUsersMap(priorityToUsersMap);
  }

  @Override
  public List<String> getTopKUsersByPriority(int k) {
    List<String> potentialUsers = new ArrayList<>();

    Map<Integer, Set<String>> priorityToUsersMap = topKRepository.getPriorityToUsersMap();
    int count = 0;

    for (Map.Entry<Integer, Set<String>> entry : priorityToUsersMap.entrySet()) {
      Set<String> users = entry.getValue();
      for (String user : users) {
        potentialUsers.add(user);
        count++;
        if (count == k) {
          return potentialUsers;
        }
      }
    }
    return potentialUsers;
  }
}
