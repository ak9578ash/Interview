package com.interview.preparation.multi_threading.fork_join_pool.example_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRecursiveTaskForArraySum extends RecursiveTask<Integer> {
  private static final int THRESHOLD = 3;
  private final List<Integer> workload;

  public CustomRecursiveTaskForArraySum(List<Integer> workload) {
    this.workload = workload;
  }

  @Override
  protected Integer compute() {
    if (workload.size() > THRESHOLD) {
      return ForkJoinTask.invokeAll(createSubtasks(workload))
          .stream()
          .map(ForkJoinTask::join)
          .reduce(0, (a,b) -> {
            return a+b;
          });
    } else {
      return computeHelper(workload);
    }
  }

  private List<CustomRecursiveTaskForArraySum> createSubtasks(List<Integer> workload) {
    List<CustomRecursiveTaskForArraySum> subtasks = new ArrayList<>();

    CustomRecursiveTaskForArraySum subtask1 = new CustomRecursiveTaskForArraySum(workload.subList(0, workload.size() / 2));
    CustomRecursiveTaskForArraySum subtask2 =
        new CustomRecursiveTaskForArraySum(workload.subList(workload.size() / 2, workload.size()));

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
