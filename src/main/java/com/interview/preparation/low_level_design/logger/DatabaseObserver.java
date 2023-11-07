package com.interview.preparation.low_level_design.logger;

public class DatabaseObserver implements LogObserver{
    @Override
    public void writeLog(int logLevel, String msg) {
        System.out.println("log is written to DB");
    }
}
