package com.interview.preparation.multi_threading.questions.foo_and_bar;

public class Demo {
  public static void main(String[] args) {
    Printer printer = new Printer();

    Thread fooThread = Thread
        .ofPlatform()
        .name("FooThread")
        .unstarted(
            () -> {
              for (int i = 0; i < 5; i++) {
                printer.printFoo();
              }
            }
        );

    Thread barThread = Thread
        .ofPlatform()
        .name("BarThread")
        .unstarted(
            () -> {
              for (int i = 0; i < 5; i++) {
                printer.printBar();
              }
            }
        );

    fooThread.start();
    barThread.start();
  }
}