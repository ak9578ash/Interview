package com.interview.preparation.multi_threading.sum.using_threads;

import java.util.List;

public class Add {
  void add(Integer a, Integer b, List<Integer> finalArray) {
    finalArray.add(a+b);
  }
}
