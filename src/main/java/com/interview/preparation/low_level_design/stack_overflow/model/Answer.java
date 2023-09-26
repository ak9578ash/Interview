package com.interview.preparation.low_level_design.stack_overflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Answer extends TextPhotoBasedEntity{

    private boolean solvedProblem; // is this answer a proper solution to the question ?
    private final List< Comment > comments;

    public Answer(Member creatingMember, String text, List< Photo > photos) {
        super(UUID.randomUUID().toString(), creatingMember, text, photos);
        this.comments = new ArrayList<>();
    }

    public void markAsASolution() {
        solvedProblem = true;
    }

    public void updateText(String text) {
        this.text = text;
        this.lastUpdated = System.currentTimeMillis();
    }

    public void addComment(Comment newComment) {
        comments.add(newComment);
    }

    public boolean isSolvedProblem() {
        return solvedProblem;
    }

    public List< Comment > getComments() {
        return comments;
    }
}
