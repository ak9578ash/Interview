package com.interview.preparation.multi_threading.inter_thread_communication.print_even_odd;

public class Printer {
    private boolean evenState = false;

    public synchronized void printEven(int number){
        while(!evenState){
            try{
                wait();
            }catch (Exception e){
                System.out.println(e.getCause());
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        evenState = false;
        notifyAll();
    }

    public synchronized void printOdd(int number){
        while(evenState){
            try{
                wait();
            }catch (Exception e){
                System.out.println(e.getCause());
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        evenState = true;
        notifyAll();

    }

}
