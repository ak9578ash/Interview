package com.interview.preparation.miscellaneous.synchronize_keyword_example;

public class Line {
    synchronized public void getLine() throws InterruptedException {
        for(int i=0;i<3;i++){
            System.out.println(i);
            try{
                Thread.sleep(100);
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }
}
