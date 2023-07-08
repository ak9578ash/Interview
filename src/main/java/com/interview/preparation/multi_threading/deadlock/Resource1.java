package com.interview.preparation.multi_threading.deadlock;

public class Resource1 {
    private String s;

    public Resource1(String s) {
        this.s = s;
    }

    public void print() {
        System.out.println(s);
    }
}
