package com.interview.preparation.low_level_design.interview.credit_system;

import java.time.LocalDateTime;
import java.util.List;

public class CreditCardMain {
  public static void main(String[] args) {
    CreditCardService creditCardService = new CreditCardService(1000);
    Operation operation1 = new Operation(1, LocalDateTime.of(2021, 1, 1, 0, 0), OperationType.AUTH, 1000, 24);
    Operation operation2 = new Operation(2, LocalDateTime.of(2021, 1, 1, 1, 0), OperationType.AUTH, 10, 24);
    Operation operation3 = new Operation(3, LocalDateTime.of(2021, 1, 3, 0, 0), OperationType.AUTH, 10, 24);
    Operation operation4 = new Operation(4, LocalDateTime.of(2021, 1, 3, 0, 10), OperationType.CAPTURE, 10, 100);
    Operation operation5 = new Operation(5, LocalDateTime.of(2021, 1, 1, 0, 10), OperationType.CAPTURE, 10, 3);
    Operation operation6 = new Operation(6, LocalDateTime.of(2021, 2, 1, 0, 0), OperationType.AUTH, 500, 240);
    Operation operation7 = new Operation(7, LocalDateTime.of(2021, 2, 7, 0, 0), OperationType.CAPTURE, 600, 6);

    List<Operation> operations = List.of(
        operation1,
        operation2,
        operation3,
        operation4,
        operation5,
        operation6,
        operation7
    );

    List<Boolean> operationVerdict = creditCardService.verifyOperation(operations);
    for (int i = 0; i < operationVerdict.size(); i++) {
      System.out.println("Operation " + operations.get(i).getId() + ": " + (operationVerdict.get(i) == Boolean.TRUE ? "Success" : "Failed"));
    }
  }
}
