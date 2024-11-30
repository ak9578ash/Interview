package com.interview.preparation.multi_threading.questions.print_even_odd;

public class Demo {
  public static void main(String[] args) {
    Printer printer = new Printer();

    Thread evenThread = Thread
        .ofVirtual()
        .name("EvenThread")
        .start(new EvenOddTask(10, printer, true));

    Thread oddThread = Thread
        .ofVirtual()
        .name("OddThread")
        .start(new EvenOddTask(10, printer, false));

    try {
        oddThread.join();
        evenThread.join();
    }catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
  }
}
