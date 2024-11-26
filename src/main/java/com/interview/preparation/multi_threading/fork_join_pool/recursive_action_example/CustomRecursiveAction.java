package com.interview.preparation.multi_threading.fork_join_pool.recursive_action_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRecursiveAction extends RecursiveAction {
  private static final int THRESHOLD = 2;
  private final String workload;

  public CustomRecursiveAction(String workload) {
    this.workload = workload;
  }

  @Override
  protected void compute() {
    if (workload.length() > THRESHOLD) {
      ForkJoinTask.invokeAll(createSubtasks());
    } else {
      processing(workload);
    }
  }

  private List<CustomRecursiveAction> createSubtasks() {
    List<CustomRecursiveAction> subtasks = new ArrayList<>();

    String partOne = workload.substring(0, workload.length() / 2);
    String partTwo = workload.substring(workload.length() / 2);

    subtasks.add(new CustomRecursiveAction(partOne));
    subtasks.add(new CustomRecursiveAction(partTwo));

    return subtasks;
  }

  private void processing(String work) {
    String result = work.toUpperCase();
    log.info("This result - (" + result + ") - was processed by " + Thread.currentThread().getName());
  }
}
