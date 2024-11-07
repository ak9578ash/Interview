package com.interview.preparation.low_level_design.messaging_app.repository;

import com.interview.preparation.low_level_design.messaging_app.models.Group;
import com.interview.preparation.low_level_design.messaging_app.models.Message;
import com.interview.preparation.low_level_design.messaging_app.models.User;

import java.util.List;

public interface MessageRepository {
    void addFriend(User user , User friend);
    void removeFriend(User user , User friend);
    void sendMessageToFriend(User sender , User receiver , Message message);
    void createGroup(User admin , List<User> groupMembers);
    void deleteGroup(String groupId);
    void sendMessageToGroup(Group group , User sender , Message message);
}
