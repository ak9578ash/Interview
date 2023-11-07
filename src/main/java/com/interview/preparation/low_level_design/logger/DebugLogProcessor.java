package com.interview.preparation.low_level_design.logger;

public class DebugLogProcessor extends LogProcessor implements LogProcessorSubject{
    public DebugLogProcessor(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }

    @Override
    public void log(int logLevel ,String msg){
        if(logLevel==DEBUG){
            System.out.println("DEBUG:" + msg);
            notifyObserver(logLevel,msg);
        }else{
            super.log(logLevel,msg);
        }
    }

    @Override
    public void addObserver(LogObserver observer) {
        this.logObserverList.add(observer);
    }

    @Override
    public void removeObserver(LogObserver observer) {
        this.logObserverList.remove(observer);
    }

    @Override
    public void notifyObserver(int logLevel ,String msg) {
        for(int i=0;i<this.logObserverList.size();i++){
            logObserverList.get(i).writeLog(logLevel,msg);
        }
    }
}
