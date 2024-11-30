package com.interview.preparation.multi_threading.questions.sum.using_threads;

import java.util.ArrayList;
import java.util.List;

public class Demo {
  public static void main(String[] args) throws InterruptedException {
    Add add = new Add();
    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = List.of(1, 2, 3);
    List<Integer> finalArray = new ArrayList<>();


    Thread th1 = Thread.ofVirtual().start(
        () -> add.add(list1.get(0), list2.get(0), finalArray)
    );

    Thread th2 = Thread.ofVirtual().start(
        () -> add.add(list1.get(1), list2.get(1), finalArray)
    );

    Thread th3 = Thread.ofVirtual().start(
        () -> add.add(list1.get(2), list2.get(2), finalArray)
    );

    th1.join();
    th2.join();
    th3.join();

    System.out.println(finalArray);
  }
}
