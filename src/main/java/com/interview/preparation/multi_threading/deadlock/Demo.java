package com.interview.preparation.multi_threading.deadlock;

public class Demo {
    public static void main(String[] args) {
        Resource1 r1 = new Resource1("Akash");
        Resource2 r2 = new Resource2("Anuj");


        Thread1 th1 = new Thread1(r1,r2);
        Thread2 th2 = new Thread2(r1,r2);

        th1.start();
        th2.start();
    }
}
