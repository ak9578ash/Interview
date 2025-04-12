package com.interview.preparation.low_level_design.pubsub.subscriber;

import com.interview.preparation.low_level_design.pubsub.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PrintSubscriber implements Subscriber {
  private final String name;

  @Override
  public void onMessage(Message message) {
    log.info("Subscriber " + name + " received message: " + message.getContent());
  }
}
