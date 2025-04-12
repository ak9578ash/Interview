package com.interview.preparation.low_level_design.pubsub.subscriber;

import com.interview.preparation.low_level_design.pubsub.model.Message;

public interface Subscriber {
  void onMessage(Message message);
}
