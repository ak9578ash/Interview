package com.interview.preparation.low_level_design.blocking_queues;

public class BlockingQueueMain {
  public static void main(String[] args) {
    int poisonPill = Integer.MAX_VALUE;
    int poisonPillPerProducer =  CustomBlockingQueueConstants.N_CONSUMERS / CustomBlockingQueueConstants.N_PRODUCERS;
    int mod = CustomBlockingQueueConstants.N_CONSUMERS % CustomBlockingQueueConstants.N_PRODUCERS;
    CustomBlockingQueue<Integer> customBlockingQueue = new CustomBlockingQueue<>(CustomBlockingQueueConstants.BOUND);

    for (int i = 1; i < CustomBlockingQueueConstants.N_PRODUCERS; i++) {
      new Thread(new Producer(customBlockingQueue, poisonPill, poisonPillPerProducer)).start();
    }

    for (int j = 0; j < CustomBlockingQueueConstants.N_CONSUMERS; j++) {
      new Thread(new Consumer(customBlockingQueue, poisonPill)).start();
    }

    new Thread(new Producer(customBlockingQueue, poisonPill, poisonPillPerProducer + mod)).start();
  }
}
