package com.interview.preparation.low_level_design.stack_overflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question extends TextPhotoBasedEntity {

    private final String title;
    private final List<Tag> tags;
    private final List<Comment> comments;
    private final List<Answer> answers;
    private QuestionStatus status;

    public Question(Member askingMember, String title, String text, List photos, List tags) {
        super(UUID.randomUUID().toString(), askingMember, text, photos);
        this.status = QuestionStatus.OPEN;
        this.title = title;

        if (tags != null) {
            this.tags = tags;
        }else {
            this.tags = new ArrayList<>();
        }

        this.comments = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public void close() { // Question Asker or Admin can close a question due to various reasons like a solution has been found or due to inactivity of users or certain other reaons
        status = QuestionStatus.CLOSED;
    }

    public void addComment(Comment newComment) {
        comments.add(newComment);
    }

    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
    }

    public String getTitle() {
        return title;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
