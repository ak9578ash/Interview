package com.interview.preparation.multi_threading.inter_thread_communication.example2;

public class Demo {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        receiver.start();
        sender.start();

    }
}
