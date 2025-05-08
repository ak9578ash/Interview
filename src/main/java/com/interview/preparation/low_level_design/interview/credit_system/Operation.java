package com.interview.preparation.low_level_design.interview.credit_system;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Operation {
  private int id;
  private LocalDateTime createdAT;
  private OperationType operationType;
  private int amount;
  private int durationOrAuthId; // duration in hours
}
