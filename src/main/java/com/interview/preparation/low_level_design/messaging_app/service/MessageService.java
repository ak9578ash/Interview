package com.interview.preparation.low_level_design.messaging_app.service;

import com.interview.preparation.design_patterns.observer.Observer;
import com.interview.preparation.low_level_design.messaging_app.exception.GroupNotFoundException;
import com.interview.preparation.low_level_design.messaging_app.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.messaging_app.models.Group;
import com.interview.preparation.low_level_design.messaging_app.models.Message;
import com.interview.preparation.low_level_design.messaging_app.models.User;
import java.util.List;

public interface MessageService {
    void addFriend(User user , User friend);
    void removeFriend(User user , User friend) throws UserNotFoundException;
    void sendMessageToFriend(User sender , User receiver , Message message) throws UserNotFoundException;
    void createGroup(User admin , List<User> groupMembers);
    void deleteGroup(User user , String groupId) throws GroupNotFoundException;
    void sendMessageToGroup(Group group , User sender , Message message);

    void addObserver(NotificationObserver observer);
    void removeObserver(NotificationObserver observer);
    void notifyObserver(User sender);
}
