package com.interview.preparation.multi_threading.inter_thread_communication.example1;

public class Deposit extends Thread{
    private Customer customer;
    public Deposit (Customer customer){
        this.customer = customer;
    }

    @Override
    public void run(){
        customer.deposit(6000);
    }
}
