package com.interview.preparation.multi_threading.executor_service.example3;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
  private final AtomicInteger threadId;
  public CustomThreadFactory() {
    this.threadId = new AtomicInteger(0);
  }
  @Override
  public Thread newThread(Runnable r) {
    return Thread.ofVirtual()
        .name("CustomThreadFactory_VirtualThread-", threadId.getAndIncrement())
        .unstarted(r);
  }
}