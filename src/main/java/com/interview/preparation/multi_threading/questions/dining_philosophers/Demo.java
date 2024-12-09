package com.interview.preparation.multi_threading.questions.dining_philosophers;

public class Demo {
  public static void main(String[] args) {
    DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

    Thread philosopher0 = Thread.ofPlatform()
        .name("philosopher0")
        .unstarted(
            new Philosopher(diningPhilosophers, 0)
        );

    Thread philosopher1 = Thread.ofPlatform()
        .name("philosopher1")
        .unstarted(
            new Philosopher(diningPhilosophers, 1)
        );

    Thread philosopher2 = Thread.ofPlatform()
        .name("philosopher2")
        .unstarted(
            new Philosopher(diningPhilosophers, 2)
        );

    Thread philosopher3 = Thread.ofPlatform()
        .name("philosopher3")
        .unstarted(
            new Philosopher(diningPhilosophers, 3)
        );

    Thread philosopher4 = Thread.ofPlatform()
        .name("philosopher4")
        .unstarted(
            new Philosopher(diningPhilosophers, 4)
        );


    philosopher0.start();
    philosopher1.start();
    philosopher2.start();
    philosopher3.start();
    philosopher4.start();

    try {
      philosopher0.join();
      philosopher1.join();
      philosopher2.join();
      philosopher3.join();
      philosopher4.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
