package com.interview.preparation.low_level_design.logger;

import com.interview.preparation.low_level_design.logger.appender.LogAppender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Logger {
  private static Logger loggerInstance;
  private LogAppender logAppender;
  private LogLevel currentLogLevel;

  private Logger(LogAppender logAppender, LogLevel currentLogLevel) {
    this.logAppender = logAppender;
    this.currentLogLevel = currentLogLevel;
  }

  public static synchronized Logger getInstance(LogAppender logAppender, LogLevel currentLogLevel) {
    if (loggerInstance == null) {
      loggerInstance = new Logger(logAppender, currentLogLevel);
    }
    return loggerInstance;
  }

  /**
   * To decide whether a log message should be logged or ignored, based on the current configured log level.
   * Suppose your system is configured to log only WARN and above,
   * Now, if someone tries to log an INFO message:
   * Then the check, fails, so the log message is ignored.
   * But if the message is ERROR, the message passes the check and is logged.
   * @param level
   * @param message
   */
  public void log(LogLevel level, String message) {
    if (level.ordinal() >= currentLogLevel.ordinal()) {
      LogMessage logMessage = new LogMessage(level, message);
      logAppender.appendLog(logMessage);
    }
  }

  public void debug(String message) {
    log(LogLevel.DEBUG, message);
  }

  public void info(String message) {
    log(LogLevel.INFO, message);
  }

  public void warning(String message) {
    log(LogLevel.WARN, message);
  }

  public void error(String message) {
    log(LogLevel.ERROR, message);
  }
}
