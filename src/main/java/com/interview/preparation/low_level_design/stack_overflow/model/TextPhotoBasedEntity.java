package com.interview.preparation.low_level_design.stack_overflow.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class TextPhotoBasedEntity {  // TextPhotoBasedEntity is an abstract class
    // since we will not be creating an object of this class
    // without specifying if this is a question, answer or comment.

    protected String id; // there is no need to expose id hence private.
    // id is used to uniquely identify an entity

    protected String text;
    protected long creationDateTime;
    protected long lastUpdated;
    protected Member creator;
    protected List< Photo > photos;
    protected Set< Integer > membersWhoDownVotedThisEntity;
    protected Set< Integer > membersWhoUpVotedThisEntity;
    protected int numberOfUsersReportedThisEntity; // members reported as spam or abuse
    protected QuestionStatus status;

    public TextPhotoBasedEntity(String id, Member creator, String text, List< Photo > photos) {
        this.id = id;
        status = QuestionStatus.DEFAULT;
        this.creator = creator;
        this.text = text;

        photos = new ArrayList<>();

        if (photos != null) {
            this.photos = photos;
        }

        membersWhoDownVotedThisEntity = new HashSet<>();
        membersWhoUpVotedThisEntity = new HashSet<>();
        creationDateTime = System.currentTimeMillis();
        lastUpdated = System.currentTimeMillis();

        numberOfUsersReportedThisEntity = 0;
    }

    public boolean equals(Object that) {
        if (that instanceof TextPhotoBasedEntity) {
            return this.id == ((TextPhotoBasedEntity) that).id;
        }
        return false;
    }

    public void upVote(int memberId) {
        if (!membersWhoUpVotedThisEntity.contains(memberId)) { // a member cannot upvote a comment that he/she has already upvoted
            if (membersWhoDownVotedThisEntity.contains(memberId)) {
                // if the member has downvoted this comment in past then upvoting it once just
                // cancels the downvote.
                membersWhoDownVotedThisEntity.remove(memberId);
            } else {
                membersWhoUpVotedThisEntity.add(memberId);
            }
        }
    }

    public void downVote(int memberId) {
        if (!membersWhoDownVotedThisEntity.contains(memberId)) { // a member cannot downvote a comment that he/she has already downvoted
            if (membersWhoUpVotedThisEntity.contains(memberId)) {
                // if the member has upvoted this comment in past then downvoting it once just
                // cancels the upvote.
                membersWhoUpVotedThisEntity.remove(memberId);
            } else {
                membersWhoDownVotedThisEntity.add(memberId);
            }
        }
    }

    // report as abuse or spam
    public void report() {
        numberOfUsersReportedThisEntity++;
    }

    public void updateText(String text) {
        this.text = text;
        lastUpdated = System.currentTimeMillis();
    }

    public void removePhoto(List< Photo > photosToBeDeleted) {
        photos.removeAll(photosToBeDeleted);
        lastUpdated = System.currentTimeMillis();
    }

    public void addPhotos(List< Photo > newPhotosToBeAdded) {
        photos.addAll(newPhotosToBeAdded);
        lastUpdated = System.currentTimeMillis();
    }

    public int getUpvoteCount() {
        return membersWhoUpVotedThisEntity.size();
    }

    public int getDownVoteCount() {
        return membersWhoDownVotedThisEntity.size();
    }

    public Member getCreator() {
        return creator;
    }

    public void delete() { // Admin can delete an entity
        status = QuestionStatus.DELETED;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public long getCreationDateTime() {
        return creationDateTime;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public List< Photo > getPhotos() {
        return photos;
    }

    public Set< Integer > getMembersWhoDownVotedThisEntity() {
        return membersWhoDownVotedThisEntity;
    }

    public Set< Integer > getMembersWhoUpvotedThisEntity() {
        return membersWhoUpVotedThisEntity;
    }


    public int getVoteCount() {
        return getUpvoteCount() - getDownVoteCount();
    }

    public int getNumberOfUsersReportedThisEntity() {
        return numberOfUsersReportedThisEntity;
    }

    public QuestionStatus getStatus() {
        return status;
    }

}

