package com.interview.preparation.multi_threading.questions.dining_philosophers;

import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DiningPhilosophers2 {
  private final Semaphore[] forks = new Semaphore[5];
  private final Semaphore maxPhilosophersAllowed = new Semaphore(4);

  public DiningPhilosophers2() {
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
    if (id == 3) {
      acquireForksLeftHanded(id);
    } else {
      acquireForksRightHanded(id);
    }

    log.info("Philosopher {} is eating", id);
    forks[id].release();
    forks[(id + 4) % 5].release();
  }

  private void acquireForksLeftHanded(int id) throws InterruptedException {
    forks[(id + 4) % 5].acquire();
    forks[id].acquire();
  }

  private void acquireForksRightHanded(int id) throws InterruptedException {
    forks[id].acquire();
    forks[(id + 4) % 5].acquire();
  }
}
