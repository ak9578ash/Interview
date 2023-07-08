package com.interview.preparation.multi_threading.inter_thread_communication.example1;

public class Demo {
    public static void main(String[] args) {
        Customer customer1 = new Customer();
        Withdraw withdraw = new Withdraw(customer1);
        Deposit deposit = new Deposit(customer1);

        withdraw.start();
        deposit.start();




    }


}
