package com.interview.preparation.miscellaneous.shallowDeepCopyEx;

public class Main {
    public static void main(String[] args) {
        int[] values = {10,20,30};
//        ShallowClass shallowClass = new ShallowClass(values);
//
//        System.out.println(shallowClass.getData()[1]);
//
//        values[1] = 100;
//
//        System.out.println(shallowClass.getData()[1]);


        DeepClass deepClass = new DeepClass(values);

        System.out.println(deepClass.getData()[1]);

        values[1] = 100;

        System.out.println(deepClass.getData()[1]);

    }
}
