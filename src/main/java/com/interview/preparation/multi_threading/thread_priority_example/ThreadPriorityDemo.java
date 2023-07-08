package com.interview.preparation.multi_threading.thread_priority_example;

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        ThreadPriority th1 = new ThreadPriority("Th1");
        ThreadPriority th2 = new ThreadPriority("Th2");
        ThreadPriority th3 = new ThreadPriority("Th3");

        System.out.println("Priority of the thread th1 is : " + th1.getPriority());
        System.out.println("Priority of the thread th2 is : " + th2.getPriority());
        System.out.println("Priority of the thread th3 is : " + th3.getPriority());

        th1.setDaemon(true);
        th2.setPriority(3);
        th3.setPriority(9);

        System.out.println("Priority of the thread th1 is : " + th1.getPriority());
        System.out.println("Priority of the thread th2 is : " + th2.getPriority());
        System.out.println("Priority of the thread th3 is : " + th3.getPriority());

        th1.start();
        th2.start();
        th3.start();


    }
}
