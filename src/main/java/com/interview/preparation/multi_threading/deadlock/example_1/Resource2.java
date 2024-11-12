package com.interview.preparation.multi_threading.deadlock.example_1;

public class Resource2 {
    private String s;

    public Resource2(String s) {
        this.s = s;
    }

    public void print() {
        System.out.println(s);
    }
}
