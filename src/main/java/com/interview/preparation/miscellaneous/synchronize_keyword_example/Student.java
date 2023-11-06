package com.interview.preparation.miscellaneous.synchronize_keyword_example;

import lombok.SneakyThrows;

public class Student extends Thread{
    private final Line line;
    public Student(Line line){
        this.line = line;
    }

    @SneakyThrows
    @Override
    public void run(){
        line.getLine();
    }
}
