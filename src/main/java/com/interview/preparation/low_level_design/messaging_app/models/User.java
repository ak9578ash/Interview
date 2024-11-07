package com.interview.preparation.low_level_design.messaging_app.models;

import lombok.Getter;

import java.util.*;

@Getter
public class User {
    private final String id;
    private final UserProfile profile;
    private final List<User> friends;
    private final Map<String, List<Message>> receiveMessage;
    private final Map<String, List<Message>> sendMessage;
    private final List<Group> groups;

    public User(UserProfile profile) {
        this.id = UUID.randomUUID().toString();
        this.profile = profile;
        this.receiveMessage = new HashMap<>();
        this.sendMessage = new HashMap<>();
        this.groups = new ArrayList<>();
        this.friends = new ArrayList<>();
    }
}
