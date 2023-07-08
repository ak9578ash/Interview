package com.interview.preparation.multi_threading.synchronization.example2;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Sender sd = new Sender();
        SendMsgThread th1 = new SendMsgThread(sd,"Akash");
        SendMsgThread th2 = new SendMsgThread(sd,"Anuj");

        th1.start();
        th2.start();
    }
}
