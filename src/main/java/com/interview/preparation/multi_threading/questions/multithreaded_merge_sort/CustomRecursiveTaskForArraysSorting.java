package com.interview.preparation.multi_threading.questions.multithreaded_merge_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRecursiveTaskForArraysSorting extends RecursiveTask<List<Integer>> {
  private static final int THRESHOLD = 2;
  private final List<Integer> workload;

  public CustomRecursiveTaskForArraysSorting(List<Integer> workload) {
    if (workload == null) {
      throw new IllegalArgumentException("Workload cannot be null");
    }
    this.workload = workload;
  }

  @Override
  protected List<Integer> compute() {
    if (workload.size() > THRESHOLD) {
      return ForkJoinTask.invokeAll(createSubtasks(workload))
          .stream()
          .map((forkJoinSubtask) -> forkJoinSubtask.join())
          .reduce((a, b) -> merge(a, b))
          .orElseThrow();
    } else {
      return processing(workload);
    }
  }

  private List<CustomRecursiveTaskForArraysSorting> createSubtasks(List<Integer> workload) {
    List<CustomRecursiveTaskForArraysSorting> sortArrayRecursiveSubtasksLists = new ArrayList<>();

    List<Integer> partSubtask = workload.subList(0, workload.size() / 2);
    List<Integer> secondSubtask = workload.subList(workload.size() / 2, workload.size());

    sortArrayRecursiveSubtasksLists.add(new CustomRecursiveTaskForArraysSorting(partSubtask));
    sortArrayRecursiveSubtasksLists.add(new CustomRecursiveTaskForArraysSorting(secondSubtask));

    return sortArrayRecursiveSubtasksLists;
  }

  private List<Integer> processing(List<Integer> workload) {
    Collections.sort(workload);
    log.info("Processing: " + workload);
    return workload;
  }

  private List<Integer> merge(List<Integer> a, List<Integer> b) {
    List<Integer> mergedArray = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < a.size() && j < b.size()) {
      if (a.get(i) < b.get(j)) {
        mergedArray.add(a.get(i));
        i++;
      } else {
        mergedArray.add(b.get(j));
        j++;
      }
    }

    while (i < a.size()) {
      mergedArray.add(a.get(i));
      i++;
    }

    while (j < b.size()) {
      mergedArray.add(b.get(j));
      j++;
    }
    return mergedArray;
  }
}
