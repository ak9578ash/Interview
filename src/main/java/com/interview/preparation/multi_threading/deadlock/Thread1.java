package com.interview.preparation.multi_threading.deadlock;

public class Thread1 extends Thread{
   private Resource1 r1;
   private Resource2 r2;

   public Thread1(Resource1 r1 , Resource2 r2){
      this.r1 = r1;
      this.r2 = r2;
   }

   @Override
   public void run(){
      synchronized (r1){
         System.out.println("Thread 1: locked resource 1");
         r1.print();
         try {
            Thread.sleep(100);
         }catch (Exception e){

         }
         synchronized (r2){
            System.out.println("Thread 1: locked resource 2");
            r2.print();
         }
      }
   }
}
