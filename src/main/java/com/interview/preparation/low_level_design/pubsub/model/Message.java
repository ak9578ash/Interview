package com.interview.preparation.low_level_design.pubsub.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Message {
  private final String content;
}