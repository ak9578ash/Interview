package com.interview.preparation.miscellaneous.synchronize_keyword_example;

public class Main {
    public static void main(String[] args) {
        Line line = new Line();

        Student s1 = new Student(line);
        Student s2 = new Student(line);

        s1.start();
        s2.start();
    }
}
