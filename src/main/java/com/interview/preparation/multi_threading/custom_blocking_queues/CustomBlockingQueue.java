package com.interview.preparation.multi_threading.custom_blocking_queues;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomBlockingQueue<T> {
  private final Queue<T> q;
  private final int limit;

  public CustomBlockingQueue(int limit) {
    this.limit = limit;
    this.q = new LinkedList<>();
  }

  public synchronized void enQueue(T t) throws InterruptedException {
    while (q.size() == limit) {
      log.info("Queue is full. " + Thread.currentThread().getName() + " is waiting....");
      this.wait();
    }
    log.info("Queue is not full. " + Thread.currentThread().getName() + " is producing ");
    q.add(t);
    this.notifyAll();
  }

  public synchronized T deQueue() throws InterruptedException {
    while (q.isEmpty()) {
      log.info("Queue is empty. " + Thread.currentThread().getName() + " is waiting....");
      this.wait();
    }
    T t = q.remove();
    log.info("Queue is not empty. " + Thread.currentThread().getName() + " is consuming ");
    this.notifyAll();
    return t;
  }
}