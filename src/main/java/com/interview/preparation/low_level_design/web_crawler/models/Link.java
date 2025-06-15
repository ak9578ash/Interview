package com.interview.preparation.low_level_design.web_crawler.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Link {
  private String url;
  private Integer depth;
}
