package com.interview.preparation.multi_threading.questions.executor_service_question;

import com.interview.preparation.multi_threading.questions.executor_service_question.deferred_task_executor.DeferredTaskExecutor;

public class CallableTaskProducer implements Runnable {
    private final CustomCallable customCallable;
    private final DeferredTaskExecutor scheduledThreadPool;

    public CallableTaskProducer(CustomCallable customCallable, DeferredTaskExecutor scheduledThreadPool) {
        this.customCallable = customCallable;
        this.scheduledThreadPool = scheduledThreadPool;
    }

    @Override
    public void run() {
        try {
            scheduledThreadPool.registerCallable(customCallable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
