package com.interview.preparation.low_level_design.rate_limiter_new.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    public String userId;
    public long limit;
    public long windowSeconds = 60;
    public long bucketCapacity = 0;
    public double refillPerSecond = 0.0;
}
