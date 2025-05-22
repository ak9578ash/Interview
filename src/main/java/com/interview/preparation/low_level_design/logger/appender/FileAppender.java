package com.interview.preparation.low_level_design.logger.appender;

import com.interview.preparation.low_level_design.logger.LogMessage;
import com.interview.preparation.low_level_design.logger.formatter.LogFormatter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileAppender implements LogAppender {
  private final LogFormatter logFormatter;
  private final String filePath;
  @Override
  public void appendLog(LogMessage logMessage) {
    // Logic to write log message to a file
  }
}
