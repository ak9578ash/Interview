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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BillSharingMain {
    static UserService userService;
    static ExpenseService expenseService;
    static NotificationService notificationService = new NotificationServiceImpl();

    public static void main(String[] args) throws ContributionExceededException, ExpenseSettledException,
            ExpenseDoesNotExistsException, InvalidExpenseStateException {
        userService = new UserService();
        expenseService = new ExpenseService(notificationService);

        createTestUsers();

        Expense lunchExpense = createExpense();
        expenseService.setExpenseStatus(lunchExpense.getId(), ExpenseStatus.PENDING);
        try {
            bifurcateExpense(lunchExpense,BifurcationStatus.EXACT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Set<User> allUsers = lunchExpense.getExpenseGroup().getGroupMembers();
        for (User user : allUsers) {
            if (user.getEmailId().equals("xyz@gmail.com")) {
                contributeToOtherExpense(lunchExpense.getId(), UserRepository.userHashMap.get(lunchExpense.getUserId()), user);
            } else {
                contributeToExpense(lunchExpense.getId(), user);
            }
        }

        if (expenseService.isExpenseSettled(lunchExpense.getId())) {
            System.out.println("Expense Settled....");
            lunchExpense.setExpenseStatus(ExpenseStatus.SETTLED);
        } else {
            System.out.println("Expense Not Settled....");
            lunchExpense.setExpenseStatus(ExpenseStatus.PENDING);
        }
    }

    private static void createTestUsers() {
        User user1 = userService.createUser("akash@gmail.com", "Akash", "7906048908");
        User user2 = userService.createUser("xyz@gmail.com", "xyz", "1234567890");
        User user3 = userService.createUser("ajay@gmail.com", "ajay", "6112482630");
        User user4 = userService.createUser("amit@gmail.com", "amit", "2509699232");
        User user5 = userService.createUser("kamal@gmail.com", "kamal", "5816355154");
    }

    private static Expense createExpense() {
        Expense lunchExpense = expenseService.createExpense("lunch expense", LocalDateTime.now(), 400, "akash@gmail.com");
        return lunchExpense;
    }

    private static void bifurcateExpense(Expense expense , BifurcationStatus bifurcationStatus) throws ExpenseDoesNotExistsException {
        expenseService.addUsersToExpense(expense.getId(), "xyz@gmail.com");
        expenseService.addUsersToExpense(expense.getId(), "ajay@gmail.com");
        expenseService.addUsersToExpense(expense.getId(), "amit@gmail.com");
        expenseService.addUsersToExpense(expense.getId(), ExpenseRepository.expenseMap.get(expense.getId()).getUserId());

        List<Split> amountList = new ArrayList<>();
        switch (bifurcationStatus){
            case EQUAL:
                bifurcateInEqual(expense);
            case EXACT:
                amountList.add(new Split(UserRepository.userHashMap.get("xyz@gmail.com"),200.0));
                amountList.add(new Split(UserRepository.userHashMap.get("ajay@gmail.com"),50.0));
                amountList.add(new Split(UserRepository.userHashMap.get("amit@gmail.com"),50.0));
                amountList.add(new Split(UserRepository.userHashMap.get(ExpenseRepository.expenseMap.get(expense.getId()).getUserId()),100.0));
                bifurcateInExact(expense ,amountList);
            default:
                bifurcateInEqual(expense);
        }

    }

    private static void bifurcateInEqual(Expense expense) throws ExpenseDoesNotExistsException {
        Double totalExpenseAmount = expense.getExpenseAmount();
        Double individualExpenseShare = totalExpenseAmount/expense.getExpenseGroup().getGroupMembers().size();

        for(User user : expense.getExpenseGroup().getGroupMembers()){
            expenseService.assignExpenseShare(expense.getId(), user.getEmailId(), individualExpenseShare);
        }
    }
    
    private static void bifurcateInExact(Expense expense , List<Split>amountList) throws ExpenseDoesNotExistsException {
        for(Split split : amountList){
            expenseService.assignExpenseShare(expense.getId() , split.getUser().getEmailId() , split.getAmount());
        }
    }

    private static void contributeToExpense(String expenseId, User user) throws ExpenseSettledException, InvalidExpenseStateException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
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
