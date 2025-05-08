package com.interview.preparation.low_level_design.interview.rate_limiter_proxy;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Request {
  private final int id;
  private final LocalDateTime createdAt;
}
