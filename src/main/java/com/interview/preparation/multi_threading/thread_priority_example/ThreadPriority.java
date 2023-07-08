package com.interview.preparation.multi_threading.thread_priority_example;

public class ThreadPriority extends Thread{
    public  ThreadPriority (String name){
        super(name);
    }
    @Override
    public void run(){
        System.out.println(this.getName());

    }
}
