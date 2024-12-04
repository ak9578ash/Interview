package com.interview.preparation.multi_threading.questions.fizz_buzz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    FizzBuzz fizzBuzz = new FizzBuzz(15, sb);

    Thread t1 = Thread.ofPlatform().name("FizzThread")
        .unstarted(
            () -> {
              try {
                fizzBuzz.printFizz();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread t2 = Thread.ofPlatform().name("BuzzThread")
        .unstarted(
            () -> {
              try {
                fizzBuzz.printBuzz();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread t3 = Thread.ofPlatform().name("FizzBuzzThread")
        .unstarted(
            () -> {
              try {
                fizzBuzz.printFizzBuzz();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    Thread t4 = Thread.ofPlatform().name("NumThread")
        .unstarted(
            () -> {
              try {
                fizzBuzz.printNum();
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            }
        );

    t1.start();
    t2.start();
    t3.start();
    t4.start();

    try{
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("Final Series: {}", sb);
  }
}
