package com.interview.preparation.multi_threading.inter_thread_communication.example1;

public class Deposit implements Runnable{
    private final Customer customer;
    private final Integer depositAmount;
    public Deposit (Customer customer, Integer depositAmount){
        this.customer = customer;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run(){
        customer.deposit(depositAmount);
    }
}
