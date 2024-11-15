package com.interview.preparation.multi_threading.foo_and_bar;

public class PrinterHelper implements Runnable {
  private final Printer printer;
  private final String methodName;

  public PrinterHelper(Printer printer, String methodName) {
    this.printer = printer;
    this.methodName = methodName;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      if (this.methodName.equalsIgnoreCase("FOO")) {
        printer.printFoo();
      } else {
        printer.printBar();
      }
    }
  }
}