package com.interview.preparation.low_level_design.top_k_priority.service;

import java.util.List;

public interface TopKService {
  void updateUserPriority(String userId, int priority);

  List<String> getTopKUsersByPriority(int k);
}
