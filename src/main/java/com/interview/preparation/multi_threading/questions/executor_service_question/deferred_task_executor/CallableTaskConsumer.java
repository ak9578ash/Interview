package com.interview.preparation.multi_threading.questions.executor_service_question.deferred_task_executor;

public class CallableTaskConsumer implements Runnable{
  private final DeferredTaskExecutor scheduledThreadPool;

  public CallableTaskConsumer( DeferredTaskExecutor scheduledThreadPool) {
    this.scheduledThreadPool = scheduledThreadPool;
  }


  @Override
  public void run() {
    try {
      scheduledThreadPool.execute();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
