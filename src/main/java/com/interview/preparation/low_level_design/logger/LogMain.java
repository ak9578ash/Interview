package com.interview.preparation.low_level_design.logger;

import com.interview.preparation.low_level_design.logger.appender.ConsoleAppender;
import com.interview.preparation.low_level_design.logger.appender.LogAppender;
import com.interview.preparation.low_level_design.logger.formatter.DefaultFormatter;
import com.interview.preparation.low_level_design.logger.formatter.LogFormatter;

public class LogMain {
  public static void main(String[] args) {
    LogFormatter logFormatter = new DefaultFormatter();
    LogAppender logAppender = new ConsoleAppender(logFormatter);
    Logger log = Logger.getInstance(logAppender, LogLevel.INFO);

    log.info("This is new log");
  }
}
