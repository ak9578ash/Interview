package com.interview.preparation.multi_threading.synchronization.example2;

public class SendMsgThread extends Thread {
    private Sender sd;
    private String msg;

    public SendMsgThread(Sender sd, String msg) {
        this.sd = sd;
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            sd.sendMessage(this.msg);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
