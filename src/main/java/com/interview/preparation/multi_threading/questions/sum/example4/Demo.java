package com.interview.preparation.multi_threading.questions.sum.example4;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    ThreadFactory virtualThreadFactory = Thread.ofVirtual().name("VirtualThread-", 0).factory();
    ExecutorService executorService = Executors.newThreadPerTaskExecutor(virtualThreadFactory);

    List<Integer> l1 = List.of(1, 2, 3);
    List<Integer> l2 = List.of(1, 2, 3);
    List<Integer> l3 = List.of(1, 2, 3);

    CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(
        () -> {
          int sum1 = sumArray(l1);
          log.info(String.valueOf(sum1));
          return sum1;
        }, executorService
    );

    CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(
        () -> {
          int sum2 = sumArray(l2);
          log.info(String.valueOf(sum2));
          return sum2;
        }, executorService
    );

    CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(
        () -> {
          int sum3 = sumArray(l3);
          log.info(String.valueOf(sum3));
          return sum3;
        }, executorService
    );

    CompletableFuture.allOf(cf1, cf2, cf3);

    Integer finalSum = Stream.of(cf1, cf2, cf3)
        .map(CompletableFuture::join)
        .reduce(0, Integer::sum);

    log.info("Final Sum: " + finalSum);
  }

  private static Integer sumArray(List<Integer> array) {
    int sum = 0;
    for (int i = 0; i < array.size(); i++) {
      sum = sum + array.get(i);
    }
    return sum;
  }
}
