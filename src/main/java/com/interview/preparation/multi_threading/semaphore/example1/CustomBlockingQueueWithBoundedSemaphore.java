package com.interview.preparation.multi_threading.semaphore.example1;

import com.interview.preparation.multi_threading.semaphore.BoundedSemaphore;
import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomBlockingQueueWithBoundedSemaphore {
  private final Queue<Integer> queue;
  private final BoundedSemaphore semaphore;

  public CustomBlockingQueueWithBoundedSemaphore() {
    queue = new LinkedList<>();
    semaphore = new BoundedSemaphore(2);
  }

  public void enqueue(Integer item) throws InterruptedException {
    semaphore.take();
    queue.add(item);
  }

  public void dequeue() throws InterruptedException {
    semaphore.release();
    Integer item = queue.poll();
    log.info("Dequeued item: {}", item);
  }
}
