package com.interview.preparation.low_level_design.testing.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Link {
  private final String url;
  private final Integer depth;
}
