package com.interview.preparation.miscellaneous.immutableClassEx;

public class Main {
    public static void main(String[] args) {
        int i = 10;
        int[] values = {10,20,30};

        Immutable immutable = new Immutable(i,values);

        System.out.println(immutable.getI());
        System.out.println(immutable.getData()[1]);

        i = 20;
        values[1] = 1000;

        System.out.println(immutable.getI());
        System.out.println(immutable.getData()[1]);
    }
}
