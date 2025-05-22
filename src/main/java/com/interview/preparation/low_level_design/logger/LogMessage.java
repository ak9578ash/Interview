package com.interview.preparation.low_level_design.logger;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class LogMessage {
  private final LogLevel logLevel;
  private final String message;
  private final LocalDateTime createdAt;
  private final String threadName;

  public LogMessage(LogLevel logLevel, String message) {
    this.logLevel = logLevel;
    this.message = message;
    this.createdAt = LocalDateTime.now();
    this.threadName = Thread.currentThread().getName();
  }
}
