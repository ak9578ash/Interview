package com.interview.preparation.low_level_design.logger.appender;

import com.interview.preparation.low_level_design.logger.LogMessage;
import com.interview.preparation.low_level_design.logger.formatter.LogFormatter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender {
  private final LogFormatter logFormatter;

  @Override
  public void appendLog(LogMessage logMessage) {
    System.out.println(logMessage.getMessage());
  }
}
