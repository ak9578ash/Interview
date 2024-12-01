package com.interview.preparation.multi_threading.questions.rate_limiting;

public class TokenProducer implements Runnable {
  private final TokenBucket tokenBucket;

  public TokenProducer(TokenBucket tokenBucket) {
    this.tokenBucket = tokenBucket;
  }

  @Override
  public void run() {
    while (true) {
      try {
        tokenBucket.addToken();
        Thread.sleep(1000); // rate at which tokens are produced
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}