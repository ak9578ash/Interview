package com.interview.preparation.low_level_design.interview.otp_generator_validator.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OtpData {
    private String code;
    private long creationTime;

    public OtpData(String code, long creationTime) {
        this.code = code;
        this.creationTime = creationTime;
    }
}
