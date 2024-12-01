package com.interview.preparation.multi_threading.questions.rate_limiting.first_implementation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenBucket {
  private final int maxTokens;
  private int currentToken;

  public TokenBucket(int maxTokens) {
    this.maxTokens = maxTokens;
    this.currentToken = 0;
  }

  public synchronized void addToken() throws InterruptedException {
    if (currentToken == maxTokens) {
      log.info("Token bucket is full. Waiting for consumer to consume token");
      this.wait();
    }

    currentToken++;
    log.info("Token added. Current token count: {}", currentToken);
    this.notifyAll();
  }

  public synchronized void getToken() throws InterruptedException {
    if (currentToken == 0) {
      log.info("Token bucket is empty. Waiting for producer to add token");
      this.wait();
    }

    currentToken--;
    log.info("Token consumed. Current token count: {}", currentToken);
    this.notifyAll();
  }
}
