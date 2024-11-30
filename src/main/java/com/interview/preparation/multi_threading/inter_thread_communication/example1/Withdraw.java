package com.interview.preparation.multi_threading.inter_thread_communication.example1;

public class Withdraw implements Runnable{
    private final Customer customer;
    private final Integer withdrawAmount;
    public Withdraw(Customer customer, Integer withdrawAmount){
        this.customer = customer;
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public void run(){
        try {
            customer.withdraw(withdrawAmount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
