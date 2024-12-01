package com.interview.preparation.multi_threading.questions.rate_limiting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenBucket {
  private final int maxTokens;
  private int currentTokens;

  public TokenBucket(int maxTokens) {
    this.maxTokens = maxTokens;
    this.currentTokens = 0;
  }

  public synchronized void addToken() throws InterruptedException {
    while (currentTokens == maxTokens) {
      log.info("Token bucket is full. Waiting for consumer to consume token");
      this.wait();
    }

    currentTokens++;
    log.info("Token added. Current token count: {}", currentTokens);
    this.notifyAll();
  }

  public synchronized void getToken() throws InterruptedException {
    while(currentTokens == 0) {
      log.info("Token bucket is empty. Waiting for producer to add token");
      this.wait();
    }

    currentTokens--;
    log.info("Token consumed. Current token count: {}", currentTokens);
    this.notifyAll();
  }
}
