package com.interview.preparation.multi_threading.questions.print_even_odd;

public class EvenOddTask implements Runnable {
  private final int max;
  private final Printer print;
  private final boolean isEvenNumber;

  public EvenOddTask(int max, Printer print, boolean isEvenNumber) {
    this.max = max;
    this.print = print;
    this.isEvenNumber = isEvenNumber;
  }

  @Override
  public void run() {
    if (isEvenNumber) {
      print.printEven(max);
    } else {
      print.printOdd(max);
    }
  }
}
