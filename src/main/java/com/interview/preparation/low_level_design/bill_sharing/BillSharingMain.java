package com.interview.preparation.low_level_design.bill_sharing;

import com.interview.preparation.low_level_design.bill_sharing.exception.ContributionExceededException;
import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseDoesNotExistsException;
import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseSettledException;
import com.interview.preparation.low_level_design.bill_sharing.exception.InvalidExpenseStateException;
import com.interview.preparation.low_level_design.bill_sharing.model.*;
import com.interview.preparation.low_level_design.bill_sharing.repository.ExpenseRepository;
import com.interview.preparation.low_level_design.bill_sharing.repository.UserRepository;
import com.interview.preparation.low_level_design.bill_sharing.service.ExpenseService;
import com.interview.preparation.low_level_design.bill_sharing.service.NotificationService;
import com.interview.preparation.low_level_design.bill_sharing.service.NotificationServiceImpl;
import com.interview.preparation.low_level_design.bill_sharing.service.UserService;
import com.interview.preparation.low_level_design.bill_sharing.utils.SplittingStrategy;
import com.interview.preparation.low_level_design.bill_sharing.utils.SplittingStrategyImpl;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.ExactSplit;
import com.interview.preparation.low_level_design.bill_sharing.utils.model.PercentageSplit;
import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BillSharingMain {
    public static UserRepository userRepository;
    public static UserService userService;

    public static ExpenseRepository expenseRepository;
    public static ExpenseService expenseService;
    public static NotificationService notificationService;

    public static SplittingStrategy splittingStrategy;

    public static void main(String[] args) throws ContributionExceededException, ExpenseSettledException,
            ExpenseDoesNotExistsException, InvalidExpenseStateException, BadRequestException {
        notificationService = new  NotificationServiceImpl();

        expenseRepository = new ExpenseRepository();
        expenseService = new ExpenseService(expenseRepository,notificationService);

        userRepository = new UserRepository();
        userService = new UserService(userRepository,expenseService);

        splittingStrategy = new SplittingStrategyImpl(expenseService);

// --------------------------------------------------------------------------------------------------------------------

        User user1 = userService.addUser("akash@gmail.com", "Akash", "7906048908");
        User user2 = userService.addUser("xyz@gmail.com", "xyz", "1234567890");
        User user3 = userService.addUser("ajay@gmail.com", "ajay", "6112482630");
        User user4 = userService.addUser("amit@gmail.com", "amit", "2509699232");

        List<User>userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

// --------------------------------------------------------------------------------------------------------------------

        Expense lunchExpense = createExpense(user1);
        expenseService.addExpense(lunchExpense);
        lunchExpense.setExpenseStatus(ExpenseStatus.PENDING);

// --------------------------------------------------------------------------------------------------------------------

        try {
            bifurcateExpense(lunchExpense,BifurcationStatus.PERCENTAGE,userList,splittingStrategy);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

// --------------------------------------------------------------------------------------------------------------------

        Set<User> allUsers = lunchExpense.getExpenseGroup().getGroupMembers();
        for (User user : allUsers) {
            contributeToExpense(lunchExpense.getId(), user);
        }

// --------------------------------------------------------------------------------------------------------------------

        if (expenseService.isExpenseSettled(lunchExpense.getId())) {
            lunchExpense.setExpenseStatus(ExpenseStatus.SETTLED);
            System.out.println("Expense Settled....");
        } else {
            lunchExpense.setExpenseStatus(ExpenseStatus.PENDING);
            System.out.println("Expense Not Settled....");
        }
    }

    private static Expense createExpense(User createdBy) {
        return expenseService.createExpense("lunch expense", LocalDateTime.now(), 400, createdBy.getEmailId());
    }

    private static void bifurcateExpense(Expense expense , BifurcationStatus bifurcationStatus , List<User>userList , SplittingStrategy splittingStrategy) throws ExpenseDoesNotExistsException, BadRequestException {
        for(User user : userList){
            expenseService.addUsersToExpense(expense.getId(), user);
        }

        switch (bifurcationStatus){
            case EQUAL:
                splittingStrategy.bifurcateInEqual(expense,userList);
            case EXACT:
                List<ExactSplit> amountList = new ArrayList<>();
                amountList.add(new ExactSplit(userList.get(0),200.0));
                amountList.add(new ExactSplit(userList.get(1),50.0));
                amountList.add(new ExactSplit(userList.get(2),50.0));
                amountList.add(new ExactSplit(userList.get(3),100.0));
                splittingStrategy.bifurcateInExact(expense,amountList);
            case PERCENTAGE:
                List<PercentageSplit>percentageList = new ArrayList<>();
                percentageList.add(new PercentageSplit(userList.get(0),expense,10.0));
                percentageList.add(new PercentageSplit(userList.get(1),expense,20.0));
                percentageList.add(new PercentageSplit(userList.get(2),expense,30.0));
                percentageList.add(new PercentageSplit(userList.get(3),expense,40.0));
                splittingStrategy.bifurcateInPercentage(expense , percentageList);
            default:
                splittingStrategy.bifurcateInEqual(expense,userList);
        }
    }

    private static void contributeToExpense(String expenseId, User user) throws ExpenseSettledException, InvalidExpenseStateException, ContributionExceededException, ExpenseDoesNotExistsException {
        Expense expense = expenseService.getExpenseById(expenseId);
        if(expense==null){
            throw new InvalidExpenseStateException("expense does not exist");
        }
        ExpenseGroup expenseGroup = expense.getExpenseGroup();

        UserShare userShare = expenseGroup.getUserContributions().get(user.getEmailId());
        Contribution contribution = new Contribution(UUID.randomUUID().toString(), userShare.getShare(), "through UPI", LocalDateTime.now());

        userService.contributeToExpense(expenseId, user.getEmailId(), contribution);

    }

    private static void contributeToOtherExpense(String expenseId, User fromUser, User toUser) throws ExpenseSettledException, InvalidExpenseStateException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();

        Contribution contribution = new Contribution(UUID.randomUUID().toString(), 200.0, "through UPI", LocalDateTime.now());

        try {
            userService.contributeToExpense(expenseId, fromUser.getEmailId(), toUser.getEmailId(), contribution);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
