package com.interview.preparation.multi_threading.inter_thread_communication.print_even_odd;

public class Demo {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread oddThread = new Thread(new EvenOddTask(10,printer,true));
        Thread evenThread = new Thread(new EvenOddTask(10,printer,false));

        evenThread.start();
        oddThread.start();
    }
}
