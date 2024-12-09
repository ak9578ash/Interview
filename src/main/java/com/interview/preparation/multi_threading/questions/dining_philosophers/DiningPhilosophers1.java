package com.interview.preparation.multi_threading.questions.dining_philosophers;

import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

/**
 * When only 4 philosophers are allowed to eat at a time.
 */
@Slf4j
public class DiningPhilosophers1 {
  private final Semaphore[] forks = new Semaphore[5];
  private final Semaphore maxPhilosophersAllowed = new Semaphore(4);

  public DiningPhilosophers1() {
    forks[0] = new Semaphore(1);
    forks[1] = new Semaphore(1);
    forks[2] = new Semaphore(1);
    forks[3] = new Semaphore(1);
    forks[4] = new Semaphore(1);
  }

  public void lifeCycleOfPhilosopher(int id) throws InterruptedException {
    while (true) {
      contemplate();
      eat(id);
    }
  }

  private void contemplate() throws InterruptedException {
    Thread.sleep(500);
  }

  private void eat(int id) throws InterruptedException {
    maxPhilosophersAllowed.acquire();

    int leftForkId = id;
    int rightForkId = (id + 4) % 5;

    forks[leftForkId].acquire();
    log.info("Philosopher {} has acquired left fork", id);
    forks[rightForkId].acquire();
    log.info("Philosopher {} has acquired right fork", id);

    log.info("Philosopher {} is eating", id);

    forks[leftForkId].release();
    log.info("Philosopher {} has released left fork", id);
    forks[rightForkId].release();
    log.info("Philosopher {} has released right fork", id);

    maxPhilosophersAllowed.release();
  }
}