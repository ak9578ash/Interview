package com.interview.preparation.multi_threading.deadlock.example_1;

public class Demo {
    public static void main(String[] args) {
        Resource1 r1 = new Resource1("Akash");
        Resource2 r2 = new Resource2("Anuj");


        Thread th1 = Thread.ofVirtual().name("Thread1").start(new Runnable1(r1, r2));
        Thread th2 = Thread.ofVirtual().name("Thread2").start(new Runnable2(r1, r2));

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
