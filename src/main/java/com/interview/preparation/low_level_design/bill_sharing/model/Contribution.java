package com.interview.preparation.low_level_design.bill_sharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class Contribution {
    private String contributionId;
    private Double contributionValue;
    private String contributionDescription;
    private LocalDateTime contributionTime;
}
