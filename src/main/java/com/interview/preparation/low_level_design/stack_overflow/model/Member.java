package com.interview.preparation.low_level_design.stack_overflow.model;

import java.util.UUID;

public class Member {
    private final String id;
    private AccountStatus accountStatus;
    private final String name;
    private final String displayName;
    private final String email;
    private final int reputation;
    private boolean isModerator;
    private boolean isAdmin;

    public Member(String name, String displayName, String email, int reputation) {
        this.id = UUID.randomUUID().toString();
        this.accountStatus = AccountStatus.ACTIVE;
        this.name = name;
        this.displayName = displayName;
        this.email = email;
        this.reputation = reputation;
    }

    public void closeAccount() {
        accountStatus = AccountStatus.CLOSED;
    }

    public void cancelAccount() {
        accountStatus = AccountStatus.CANCELED;
    }

    public void blacklist() {
        accountStatus = AccountStatus.BLACKLISTED;
    }

    public void block() {
        accountStatus = AccountStatus.BLOCKED;
    }

    public boolean blockMember(Member member) {
        if (isAdmin) {
            member.block();
        }
        return false;
    }

    public boolean unblockMember(Member member) {
        if (isAdmin) {
            member.accountStatus = AccountStatus.ACTIVE;
        }
        return false;
    }

    public boolean closeQuestion(Question question) {
        // only moderator, admin or creator of the question can close a question
        if (isAdmin || isModerator || this.id == question.getCreator().getId()) {
            question.close();
        }
        return false;
    }

    public void promoteToAdmin() {
        isAdmin = true;
    }

    public void promoteToModerator() {
        isModerator = true;
    }


    public int getReputation() {
        return reputation;
    }

    public String getId() {
        return id;
    }

    public AccountStatus getStatus() {
        return accountStatus;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}

