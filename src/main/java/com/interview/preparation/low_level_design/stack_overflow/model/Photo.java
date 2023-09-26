package com.interview.preparation.low_level_design.stack_overflow.model;

import java.util.UUID;

public class Photo {
    private final String id;
    private final String photoPath;
    private final long creationDate;
    private final Member creatingMember;

    public Photo(String photoPath, Member creator) {
        this.id = UUID.randomUUID().toString();
        this.photoPath = photoPath;
        this.creationDate = System.currentTimeMillis();
        this.creatingMember = creator;
    }

    public boolean equals(Object that) {
        if (that instanceof Photo) {
            return this.id == ((Photo) that).id;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public Member getCreatingMember() {
        return creatingMember;
    }

}

