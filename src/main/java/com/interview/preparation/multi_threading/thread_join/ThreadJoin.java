package com.interview.preparation.multi_threading.thread_join;

class ThreadJoin extends Thread {
    // overriding the run method
    @Override
    public void run() {
        for (int j = 0; j < 2; j++) {
            try {
                Thread.sleep(300);
                System.out.println("The current thread name is: " + Thread.currentThread().getName());
            }
            catch (Exception e) {
                System.out.println("The exception has been caught: " + e);
            }
            System.out.println(j);
        }
    }
}
