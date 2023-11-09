package com.interview.preparation.low_level_design.logger.subject;

import com.interview.preparation.low_level_design.logger.observer.LogObserver;

public interface LogProcessorSubject {
    void addObserver(LogObserver observer);
    void removeObserver(LogObserver observer);
    void notifyObserver(int logLevel ,String msg);
}
