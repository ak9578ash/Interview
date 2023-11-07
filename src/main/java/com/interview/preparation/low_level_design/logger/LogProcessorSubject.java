package com.interview.preparation.low_level_design.logger;

import com.interview.preparation.design_patterns.observer.Observer;

public interface LogProcessorSubject {
    void addObserver(LogObserver observer);
    void removeObserver(LogObserver observer);
    void notifyObserver(int logLevel ,String msg);
}
