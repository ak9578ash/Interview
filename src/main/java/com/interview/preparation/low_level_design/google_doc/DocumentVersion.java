package com.interview.preparation.low_level_design.google_doc;

import java.time.LocalDateTime;

public class DocumentVersion {
  private final String content;
  private final LocalDateTime timestamp;
  private final String userId;

  public DocumentVersion(String content, String userId) {
    this.content = content;
    this.timestamp = LocalDateTime.now();
    this.userId = userId;
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getUserId() {
    return userId;
  }
}
