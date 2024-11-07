package com.interview.preparation.low_level_design.messaging_app.models;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Group {
    private String id;
    private User admin;
    private List<User> groupMembers;

    public Group(User admin , List<User>groupMembers){
        this.id = UUID.randomUUID().toString();
        this.admin = admin;
        this.groupMembers = groupMembers;
    }
}
