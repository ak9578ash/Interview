package com.interview.preparation.multi_threading.questions.sum.using_completable_future;

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
    Add add = new Add();
    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = List.of(1, 2, 3);

    ThreadFactory threadFactory = Thread.ofVirtual().name("VirtualThread-", 0).factory();
    ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory);

    CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(
        () -> {
          log.info("Adding first element");
          return add.add(list1.get(0), list2.get(0));
        }, executorService
    );

    CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(
        () -> {
          log.info("Adding second element");
          return add.add(list1.get(1), list2.get(1));
        }, executorService
    );
    CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(
        () -> {
          log.info("Adding third element");
          return add.add(list1.get(2), list2.get(2));
        }, executorService
    );

    CompletableFuture.allOf(cf1, cf2, cf3);

    List<Integer> finalArrays = Stream.of(cf1, cf2, cf3)
        .map(CompletableFuture::join)
        .toList();

    log.info(finalArrays.toString());
  }
}