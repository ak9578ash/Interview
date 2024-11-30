package com.interview.preparation.multi_threading.inter_thread_communication.example1;

public class Demo {
    public static void main(String[] args) {
        Customer customer = new Customer(10000);

        Thread withdrawThread = Thread.ofPlatform()
            .name("WithdrawThread")
            .unstarted(new Withdraw(customer, 15000));


        Thread depositThread1 = Thread.ofPlatform()
            .name("DepositThread1")
            .unstarted(new Deposit(customer, 2000));
        Thread depositThread2 = Thread.ofPlatform()
            .name("DepositThread2")
            .unstarted(new Deposit(customer, 2000));
        Thread depositThread3 = Thread.ofPlatform()
            .name("DepositThread3")
            .unstarted(new Deposit(customer, 2000));


        withdrawThread.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        depositThread1.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        depositThread2.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        depositThread3.start();

        try {
            withdrawThread.join();
            depositThread1.join();
            depositThread2.join();
            depositThread3.join();
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
