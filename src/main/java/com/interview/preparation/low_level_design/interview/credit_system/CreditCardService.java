package com.interview.preparation.low_level_design.interview.credit_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditCardService {
  private final Map<Integer, Operation> operationMap;
  private int availableCreditCardLimit;

  public CreditCardService(int availableCreditCardLimit) {
    this.availableCreditCardLimit = availableCreditCardLimit;
    this.operationMap = new HashMap<>();
  }

  public List<Boolean> verifyOperation(List<Operation> operations) {
    List<Boolean> operationVerdict = new ArrayList<>();
    for (Operation operation : operations) {
      if (operation.getOperationType() == OperationType.AUTH) {
        removeExpiredAuthOperations(operation);

        int authAmount = operation.getAmount();
        if (authAmount <= availableCreditCardLimit) {
          availableCreditCardLimit = availableCreditCardLimit - authAmount;
          operationVerdict.add(Boolean.TRUE);
          operationMap.put(operation.getId(), operation);
        } else {
          operationVerdict.add(Boolean.FALSE);
        }
      } else {
        int captureAmount = operation.getAmount();
        int authId = operation.getDurationOrAuthId();

        if (operationMap.containsKey(authId)) {
          Operation authOperation = operationMap.get(authId);
          if (authOperation.getAmount() >= captureAmount) {
            authOperation.setAmount(authOperation.getAmount() - captureAmount);
            operationVerdict.add(Boolean.TRUE);
          } else {
            operationVerdict.add(Boolean.FALSE);
          }
        } else {
          operationVerdict.add(Boolean.FALSE);
        }
      }
    }
    return operationVerdict;
  }

  private void removeExpiredAuthOperations(Operation operation) {
    LocalDateTime currentDate = operation.getCreatedAT();
    for (Map.Entry<Integer, Operation> entry : operationMap.entrySet()) {
      int authId = entry.getKey();
      Operation authOperation = entry.getValue();
      int amount = authOperation.getAmount();
      if (authOperation.getCreatedAT().plusHours(authOperation.getDurationOrAuthId()).isBefore(currentDate)) {
        operationMap.remove(authId);
        availableCreditCardLimit = availableCreditCardLimit + amount;
      }
    }
  }
}
