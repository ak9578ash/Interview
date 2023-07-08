package com.interview.preparation.multi_threading.synchronization.example1;

public class Demo {
    public static void main(String[] args) {
        Table obj = new Table();
        Thread1 t1 = new Thread1(obj);
        Thread2 t2 = new Thread2(obj);
        t1.start();
        t2.start();
    }
}
