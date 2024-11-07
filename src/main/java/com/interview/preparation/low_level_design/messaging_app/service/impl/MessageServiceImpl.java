package com.interview.preparation.low_level_design.messaging_app.service.impl;

import com.interview.preparation.design_patterns.observer.Observer;
import com.interview.preparation.low_level_design.messaging_app.exception.GroupNotFoundException;
import com.interview.preparation.low_level_design.messaging_app.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.messaging_app.models.Group;
import com.interview.preparation.low_level_design.messaging_app.models.Message;
import com.interview.preparation.low_level_design.messaging_app.models.User;
import com.interview.preparation.low_level_design.messaging_app.repository.MessageRepository;
import com.interview.preparation.low_level_design.messaging_app.service.MessageService;
import com.interview.preparation.low_level_design.messaging_app.service.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final List<NotificationObserver> observerList;
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.observerList = new ArrayList<>();
    }

    @Override
    public void addFriend(User user, User friend) {
        user.getFriends().add(friend);
    }

    @Override
    public void removeFriend(User user, User friend) throws UserNotFoundException {
        for (int i = 0; i < user.getFriends().size(); i++) {
            if (user.getFriends().get(i).getId() == friend.getId()) {
                user.getFriends().remove(friend);
                return;
            }
        }
        throw new UserNotFoundException("given friend does not exist");
    }

    @Override
    public void sendMessageToFriend(User sender, User receiver, Message message) throws UserNotFoundException {
        for (int i = 0; i < sender.getFriends().size(); i++) {
            if (sender.getFriends().get(i).getId() == receiver.getId()) {
                sender.getSendMessage().get(receiver.getId()).add(message);
                receiver.getReceiveMessage().get(sender.getId()).add(message);
                return;
            }
        }
        throw new UserNotFoundException("given receiver is not a friend");
    }

    @Override
    public void createGroup(User admin, List<User> groupMembers) {
        Group group = new Group(admin, groupMembers);
        admin.getGroups().add(group);
    }

    @Override
    public void deleteGroup(User user, String groupId) throws GroupNotFoundException {
        for (int i = 0; i < user.getGroups().size(); i++) {
            if (user.getGroups().get(i).getId() == groupId) {
                Group group = user.getGroups().get(i);
                user.getGroups().remove(group);
                return;
            }
        }
        throw new GroupNotFoundException("group does not exist");
    }

    @Override
    public void sendMessageToGroup(Group group, User sender, Message message) {
        for(int i=0;i<group.getGroupMembers().size();i++){
            User receiver = group.getGroupMembers().get(i);
            sendMessageToUser(sender,receiver,message);
        }
        notifyObserver(sender);
    }

    @Override
    public void addObserver(NotificationObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(NotificationObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(User sender) {
        for(int i=0;i<observerList.size();i++){
            observerList.get(i).notify(sender);
        }
    }

    private void sendMessageToUser(User sender , User receiver , Message message){
        sender.getSendMessage().get(receiver.getId()).add(message);
        receiver.getReceiveMessage().get(sender.getId()).add(message);
    }
}
