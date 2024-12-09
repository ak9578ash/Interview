package com.interview.preparation.multi_threading.questions.dining_philosophers;

public class Philosopher implements Runnable {
  private final int id;
  private final DiningPhilosophers1 diningPhilosophers;

  public Philosopher(DiningPhilosophers1 diningPhilosophers, int id) {
    this.diningPhilosophers = diningPhilosophers;
    this.id = id;
  }

  @Override
  public void run() {
    try {
      diningPhilosophers.lifeCycleOfPhilosopher(id);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}