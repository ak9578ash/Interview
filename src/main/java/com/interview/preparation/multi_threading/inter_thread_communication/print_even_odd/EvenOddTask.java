package com.interview.preparation.multi_threading.inter_thread_communication.print_even_odd;

public class EvenOddTask implements Runnable{
    private int max;
    private Printer print;
    private boolean isEvenNumber;

    public EvenOddTask(int max,Printer print,boolean isEvenNumber){
        this.max = max;
        this.print = print;
        this.isEvenNumber = isEvenNumber;
    }
    @Override
    public void run() {
        int number = isEvenNumber ? 2 : 1;
        while (number <= max) {
            if (isEvenNumber) {
                print.printEven(number);
            } else {
                print.printOdd(number);
            }
            number += 2;
        }
    }
}
