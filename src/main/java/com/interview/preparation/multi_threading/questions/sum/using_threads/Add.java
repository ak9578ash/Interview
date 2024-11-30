package com.interview.preparation.multi_threading.questions.sum.using_threads;

import java.util.List;

public class Add {
  synchronized void add(Integer a, Integer b, List<Integer> finalArray) {
    finalArray.add(a+b);
  }
}
