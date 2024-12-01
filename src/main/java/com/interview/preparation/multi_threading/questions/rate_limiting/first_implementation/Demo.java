package com.interview.preparation.multi_threading.questions.rate_limiting.first_implementation;
public class Demo {
  public static void main(String[] args) {
    TokenBucket tokenBucket = new TokenBucket(5);

    Thread tokenProducerThread = Thread.ofPlatform()
        .name("ProducerThread")
        .unstarted(
            new TokenProducer(tokenBucket)
        );

    Thread tokenConsumerThread1 = Thread.ofPlatform()
        .name("ConsumerThread1")
        .unstarted(
            new TokenConsumer(tokenBucket)
        );

    Thread tokenConsumerThread2 = Thread.ofPlatform()
        .name("ConsumerThread2")
        .unstarted(
            new TokenConsumer(tokenBucket)
        );

    tokenProducerThread.start();

    try {
      Thread.sleep(5000);
    }catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    tokenConsumerThread1.start();
    tokenConsumerThread2.start();
  }
}
