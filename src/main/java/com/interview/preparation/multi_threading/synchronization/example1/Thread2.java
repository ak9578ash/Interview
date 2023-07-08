package com.interview.preparation.multi_threading.synchronization.example1;

public class Thread2 extends Thread{
    Table t;
    public Thread2 (Table t){
        this.t = t;
    }

    @Override
    public void run(){
        t.printTable(100);
    }

}
