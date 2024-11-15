package com.interview.preparation.multi_threading.foo_and_bar;

public class Demo {
  public static void main(String[] args) {
    Printer printer = new Printer();

    Thread fooThread = Thread
        .ofPlatform()
        .name("FooThread")
        .unstarted(new PrinterHelper(printer, "FOO"));

    Thread barThread = Thread
        .ofPlatform()
        .name("BarThread")
        .unstarted(new PrinterHelper(printer, "BAR"));

    fooThread.start();
    barThread.start();
  }
}