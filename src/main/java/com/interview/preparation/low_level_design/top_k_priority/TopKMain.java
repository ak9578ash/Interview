package com.interview.preparation.low_level_design.top_k_priority;

import com.interview.preparation.low_level_design.top_k_priority.repository.TopKRepository;
import com.interview.preparation.low_level_design.top_k_priority.repository.TopKRepositoryImpl;
import com.interview.preparation.low_level_design.top_k_priority.service.TopKService;
import com.interview.preparation.low_level_design.top_k_priority.service.TopKServiceImpl;
import com.interview.preparation.low_level_design.top_k_priority.util.SortByPriorityInDesc;
import java.util.Comparator;
import java.util.List;

public class TopKMain {
  public static void main(String[] args) {
    Comparator<Integer> sortByPriorityInDesc = new SortByPriorityInDesc();
    TopKRepository topKRepository = new TopKRepositoryImpl(sortByPriorityInDesc);
    TopKService topKService = new TopKServiceImpl(topKRepository);


    topKService.updateUserPriority("akash", 2);
    topKService.updateUserPriority("anuj", 2);
    topKService.updateUserPriority("akash", 3);
    topKService.updateUserPriority("apoorv", 10);
    topKService.updateUserPriority("akash", 20);

    List<String> users = topKService.getTopKUsersByPriority(4);

    for(String user : users) {
      System.out.println(user);
    }
  }
}
