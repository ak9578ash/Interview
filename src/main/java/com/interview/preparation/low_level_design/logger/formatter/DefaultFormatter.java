package com.interview.preparation.low_level_design.logger.formatter;

import com.interview.preparation.low_level_design.logger.LogMessage;

public class DefaultFormatter implements LogFormatter{
  @Override
  public String format(LogMessage logMessage) {
    return String.format("[%s] [%s] [%s] %s",
        logMessage.getCreatedAt(), logMessage.getThreadName(), logMessage.getLogLevel(), logMessage.getMessage());
  }
}
