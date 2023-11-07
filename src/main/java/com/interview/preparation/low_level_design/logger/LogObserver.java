package com.interview.preparation.low_level_design.logger;

public interface LogObserver {
    void writeLog(int logLevel , String msg);
}
