package com.interview.preparation.multi_threading.questions.priniting_number_series;

public class NumberSeriesHelper implements Runnable{
  private final NumberSeries numberSeries;
  private final int n;

  private final String methodName;

  public NumberSeriesHelper(NumberSeries numberSeries, int n, String methodName) {
    this.numberSeries = numberSeries;
    this.n = n;
    this.methodName = methodName;
  }

  @Override
  public void run() {
    if (methodName.equalsIgnoreCase("printZero")) {
      for (int i = 0; i < n; i++) {
        numberSeries.printZero();
      }
    } else if (methodName.equalsIgnoreCase("printEven")) {
      for (int i = 0; i < n/2; i++) {
        numberSeries.printEven();
      }
    } else if (methodName.equalsIgnoreCase("printOdd")) {
      int x = n%2 == 0 ? n/2 : n/2 + 1;
      for (int i = 0; i < x; i++) {
        numberSeries.printOdd();
      }
    }
  }
}
