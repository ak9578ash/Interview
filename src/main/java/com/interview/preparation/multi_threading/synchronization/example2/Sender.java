package com.interview.preparation.multi_threading.synchronization.example2;

public class Sender {
    synchronized public void sendMessage(String msg) throws InterruptedException {
        System.out.println("\nSending a Message: "  + msg);
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("Thread interrupted.");
        }
        System.out.println("\n" +msg+ " Sent");
    }
}
