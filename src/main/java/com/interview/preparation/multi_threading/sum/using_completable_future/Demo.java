package com.interview.preparation.multi_threading.sum.using_completable_future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class Demo {
  public static void main(String[] args) {
    Add add = new Add();
    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = List.of(1, 2, 3);

    ThreadFactory threadFactory = Thread.ofVirtual().factory();
    ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory);

    CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(
        () -> add.add(list1.get(0), list2.get(0)), executorService
    );

    CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(
        () -> add.add(list1.get(1), list2.get(1)), executorService
    );
    CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(
        () -> add.add(list1.get(2), list2.get(2)), executorService
    );

    CompletableFuture.allOf(cf1, cf2, cf3);

    List<Integer> finalArrays = Stream.of(cf1, cf2, cf3)
        .map(CompletableFuture::join)
        .toList();

    System.out.println(finalArrays);
  }
}