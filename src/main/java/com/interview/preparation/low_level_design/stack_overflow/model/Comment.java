package com.interview.preparation.low_level_design.stack_overflow.model;

import java.util.List;
import java.util.UUID;

public class Comment extends TextPhotoBasedEntity{
    public Comment(Member commenter, String text, List< Photo > photos) {
        super(UUID.randomUUID().toString(),commenter, text, photos);
    }
}
