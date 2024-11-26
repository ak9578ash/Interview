package com.interview.preparation.multi_threading.fork_join_pool.recursive_task_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRecursiveTask extends RecursiveTask<String> {
  private static final int THRESHOLD = 2;
  private final String workload;

  public CustomRecursiveTask(String workload) {
    this.workload = workload;
  }

  @Override
  protected String compute() {
    if (workload.length() > THRESHOLD) {
      return ForkJoinTask.invokeAll(createSubtasks())
          .stream()
          .map(ForkJoinTask::join)
          .reduce("", String::concat);
    } else {
      return processing(workload);
    }
  }

  private List<CustomRecursiveTask> createSubtasks() {
    List<CustomRecursiveTask> subtasks = new ArrayList<>();

    String partOne = workload.substring(0, workload.length() / 2);
    String partTwo = workload.substring(workload.length() / 2);

    subtasks.add(new CustomRecursiveTask(partOne));
    subtasks.add(new CustomRecursiveTask(partTwo));

    return subtasks;
  }

  private String processing(String work) {
    String result = work.toUpperCase();
    log.info("This result - (" + result + ") - was processed by " + Thread.currentThread().getName());
    return result;
  }
}