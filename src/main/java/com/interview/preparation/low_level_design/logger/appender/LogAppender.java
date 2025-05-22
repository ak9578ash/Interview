package com.interview.preparation.low_level_design.logger.appender;

import com.interview.preparation.low_level_design.logger.LogMessage;

public interface LogAppender {
  void appendLog(LogMessage logMessage);
}
