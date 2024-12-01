package com.interview.preparation.multi_threading.questions.rate_limiting.first_implementation;

public class TokenConsumer implements Runnable{
  private final TokenBucket tokenBucket;

  public TokenConsumer(TokenBucket tokenBucket) {
    this.tokenBucket = tokenBucket;
  }

  @Override
  public void run() {
    while (true) {
      try {
        tokenBucket.getToken();
        Thread.sleep(2000); // to simulate heavy processing
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}