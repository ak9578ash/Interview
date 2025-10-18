package com.interview.preparation.low_level_design.rate_limiter_new.controller;

import com.interview.preparation.low_level_design.rate_limiter_new.model.ApiResponse;
import com.interview.preparation.low_level_design.rate_limiter_new.model.CreateUserRequest;
import com.interview.preparation.low_level_design.rate_limiter_new.model.RateLimitAlgorithmEnum;
import com.interview.preparation.low_level_design.rate_limiter_new.model.UserRateLimit;
import com.interview.preparation.low_level_design.rate_limiter_new.service.RateLimiterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RequestMapping("v0/api")
public class RateLimitController {
    private final RateLimiterService rateLimiterService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest req) {
        if (req.userId == null || req.userId.isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse("userId required"));
        }

        UserRateLimit u = new UserRateLimit(req.userId, req.limit, req.windowSeconds, req.bucketCapacity);
        if (req.bucketCapacity > 0) {
            u.setBucketCapacity(req.bucketCapacity);
        }
        if (req.refillPerSecond > 0) {
            u.setRefillPerSecond(req.refillPerSecond);
        }

        rateLimiterService.createOrUpdateUser(u);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse("user created"));
    }

    @PostMapping("/requests/{userId}")
    public ResponseEntity<?> request(@PathVariable String userId) {
        boolean allowed = rateLimiterService.allowRequest(userId);
        if (allowed) return ResponseEntity.ok(new ApiResponse("allowed"));
        return ResponseEntity.status(429).body(new ApiResponse("rate limit exceeded"));
    }

    @PostMapping("/algorithm")
    public ResponseEntity<?> setAlgorithm(@RequestParam String value) {
        try {
            RateLimitAlgorithmEnum algo = RateLimitAlgorithmEnum.valueOf(value);
            rateLimiterService.setAlgorithm(algo);
            return ResponseEntity.ok(new ApiResponse("Algorithm set to " + value));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid algorithm"));
        }
    }

    @GetMapping("/algorithm")
    public ResponseEntity<?> getAlgorithm() {
        return ResponseEntity.ok(Map.of("currentAlgorithm", rateLimiterService.getAlgorithm().name()));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(rateLimiterService.listUsers());
    }
}
