package com.interview.preparation.multi_threading.fork_join_pool.example_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRecursiveTaskForSum extends RecursiveTask<Integer> {
  private static final int THRESHOLD = 3;
  private final List<Integer> workload;

  public CustomRecursiveTaskForSum(List<Integer> workload) {
    this.workload = workload;
  }

  @Override
  protected Integer compute() {
    if (workload.size() > THRESHOLD) {
      return ForkJoinTask.invokeAll(createSubtasks(workload))
          .stream()
          .map(ForkJoinTask::join)
          .reduce(0, Integer::sum);
    } else {
      return computeHelper(workload);
    }
  }

  private List<CustomRecursiveTaskForSum> createSubtasks(List<Integer> workload) {
    List<CustomRecursiveTaskForSum> subtasks = new ArrayList<>();

    CustomRecursiveTaskForSum subtask1 = new CustomRecursiveTaskForSum(workload.subList(0, workload.size() / 2));
    CustomRecursiveTaskForSum subtask2 =
        new CustomRecursiveTaskForSum(workload.subList(workload.size() / 2, workload.size()));

    subtasks.add(subtask1);
    subtasks.add(subtask2);

    return subtasks;
  }

  private Integer computeHelper(List<Integer> workload) {
    int sum = 0;

    for (int i = 0; i < workload.size(); i++) {
      sum = sum + workload.get(i);
    }
    log.info("This result - (" + sum + ") - was processed by " + Thread.currentThread().getName());
    return sum;
  }
}
