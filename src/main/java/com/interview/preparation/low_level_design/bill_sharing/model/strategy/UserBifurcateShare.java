package com.interview.preparation.low_level_design.bill_sharing.model.strategy;

import com.interview.preparation.low_level_design.bill_sharing.model.User;
import lombok.Getter;

@Getter
public class UserBifurcateShare {
    private final User user;
    private final double share;

    public UserBifurcateShare(User user, double share) {
        this.user = user;
        this.share = share;
    }
}
