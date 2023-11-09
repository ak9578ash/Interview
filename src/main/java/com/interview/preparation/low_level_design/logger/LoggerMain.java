package com.interview.preparation.low_level_design.logger;

import com.interview.preparation.low_level_design.logger.observer.impl.ConsoleObserver;
import com.interview.preparation.low_level_design.logger.observer.impl.DatabaseObserver;
import com.interview.preparation.low_level_design.logger.observer.impl.FileObserver;
import com.interview.preparation.low_level_design.logger.subject.DebugLogProcessor;
import com.interview.preparation.low_level_design.logger.subject.ErrorLogProcessor;
import com.interview.preparation.low_level_design.logger.subject.InfoLogProcessor;
import com.interview.preparation.low_level_design.logger.subject.LogProcessor;

public class LoggerMain {
    public static void main(String[] args) {
        ConsoleObserver consoleObserver = new ConsoleObserver();
        DatabaseObserver databaseObserver = new DatabaseObserver();
        FileObserver fileObserver = new FileObserver();

        ErrorLogProcessor errorLogProcessor = new ErrorLogProcessor(null);
        DebugLogProcessor debugLogProcessor = new DebugLogProcessor(errorLogProcessor);
        InfoLogProcessor infoLogProcessor = new InfoLogProcessor(debugLogProcessor);

        infoLogProcessor.addObserver(fileObserver);
        infoLogProcessor.addObserver(databaseObserver);
        infoLogProcessor.addObserver(consoleObserver);

        debugLogProcessor.addObserver(consoleObserver);

        errorLogProcessor.addObserver(consoleObserver);

        LogProcessor logger = infoLogProcessor;
        logger.log(LogProcessor.DEBUG,"this is debugger");
    }
}
