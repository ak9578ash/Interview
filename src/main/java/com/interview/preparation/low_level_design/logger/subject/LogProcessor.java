package com.interview.preparation.low_level_design.logger.subject;

import com.interview.preparation.low_level_design.logger.observer.LogObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class LogProcessor  {
    public static int INFO = 1;
    public static int ERROR = 2;
    public static int DEBUG = 3;
    private final LogProcessor nextLogProcessor;
    public final List<LogObserver>logObserverList;

    public LogProcessor(LogProcessor nextLogProcessor) {
        this.nextLogProcessor = nextLogProcessor;
        this.logObserverList = new ArrayList<>();
    }

    public void log(int logLevel , String msg){
        if(nextLogProcessor!=null){
            nextLogProcessor.log(logLevel,msg);
        }
    }
}
