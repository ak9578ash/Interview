package com.interview.preparation.multi_threading.test_sleep_method;

public class TestCallRun2 extends Thread{
    public void run(){
        System.out.println(this.getName());
        for(int i=1;i<5;i++){
            try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
            System.out.println(i);
        }
    }
    public static void main(String args[]){
        TestCallRun2 t1=new TestCallRun2();
        TestCallRun2 t2=new TestCallRun2();
        t1.setName("T1");
        t2.setName("T2");
        t1.run();
        t2.run();
    }
}
