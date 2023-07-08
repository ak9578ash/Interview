package com.interview.preparation.multi_threading.using_runnable_interface;

public class RunnableDemo implements Runnable{
    private Thread t;
    private String threadName;

    RunnableDemo( String name) {
        this.threadName = name;
        System.out.println("Creating " +  this.threadName );
    }

    @Override
    public void run() {
        System.out.println("Running " +  this.threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + this.threadName + ", " + i);
                // Let the thread sleep for a while.
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
