package com.interview.preparation.multi_threading.questions.executor_service_question.custom_schedule_thread_pool;

import com.interview.preparation.multi_threading.questions.executor_service_question.CustomCallable;

import java.util.concurrent.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoolThreadCallable implements Runnable {
    private final BlockingQueue<CustomCallable> taskQueue;
    private Thread thread;
    private boolean isStopped;

    public PoolThreadCallable(BlockingQueue<CustomCallable> queue) {
        this.taskQueue = queue;
        this.thread = null;
        this.isStopped = false;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped) {
            synchronized (this.taskQueue) {
                long sleepFor = findSleepDuration();
                if (sleepFor <= 0) {
                    try {
                        CustomCallable task = taskQueue.take();
                        log.info(" ExecutedAt: {} ScheduledAt: {} Task: {}", System.currentTimeMillis() / 1000, task.getExecutedAt() / 1000, task.getCallable().call());
                    } catch (Exception e) {
                        log.error("Error executing callable task");
                    }
                } else {
                    try {
                        this.taskQueue.wait(sleepFor);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private long findSleepDuration() {
        if (taskQueue.isEmpty()) {
            return 1000L;
        }
        long currentTime = System.currentTimeMillis();
        return taskQueue.peek().getExecutedAt() - currentTime;
    }

    public synchronized void doStop() {
        isStopped = true;
        this.thread.interrupt();
    }
}
