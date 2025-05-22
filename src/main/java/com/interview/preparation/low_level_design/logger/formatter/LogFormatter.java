package com.interview.preparation.low_level_design.logger.formatter;

import com.interview.preparation.low_level_design.logger.LogMessage;

public interface LogFormatter {
  String format(LogMessage logMessage);
}
