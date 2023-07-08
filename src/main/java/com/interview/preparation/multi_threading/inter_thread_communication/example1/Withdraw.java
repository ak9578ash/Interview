package com.interview.preparation.multi_threading.inter_thread_communication.example1;

public class Withdraw extends Thread{
    private Customer customer;
    public Withdraw(Customer customer){
        this.customer = customer;
    }

    @Override
    public void run(){
        customer.withdraw(15000);
    }
}
