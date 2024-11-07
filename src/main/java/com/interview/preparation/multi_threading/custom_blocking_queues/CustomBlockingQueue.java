package com.interview.preparation.multi_threading.custom_blocking_queues;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueue<T> {
  private final Queue<T> q;
  private final int limit;

  public CustomBlockingQueue(int limit){
    this.limit = limit;
    this.q = new LinkedList<>();
  }

  public synchronized void enQueue(T t) throws InterruptedException {
    while (q.size() == limit) {
      System.out.println("Queue is full."+ Thread.currentThread().getName() + " is waiting....");
      wait();
    }
    System.out.println("Queue is not full. "+ Thread.currentThread().getName() + " is producing "+ t);
    q.add(t);
    notifyAll();
  }

  public synchronized T deQueue() throws InterruptedException {
    while (q.isEmpty()) {
      System.out.println("Queue is empty." + Thread.currentThread().getName() + " is waiting....");
      wait();
    }
    T t = q.remove();
    System.out.println("Queue is not empty. "+ Thread.currentThread().getName() + " is consuming "+ t);
    notifyAll();
    return t;
  }
}
