package com.interview.preparation.low_level_design.rate_limiter_new.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BucketInfo {
    public double tokens;
    public long lastRefill;
}
