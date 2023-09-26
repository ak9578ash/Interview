package com.interview.preparation.low_level_design.stack_overflow.service;

import com.interview.preparation.low_level_design.stack_overflow.exception.EntityNotFoundException;
import com.interview.preparation.low_level_design.stack_overflow.model.TextPhotoBasedEntity;
import com.interview.preparation.low_level_design.stack_overflow.repository.TextAndPhotoRepository;

public class TextAndPhotoService {
    private final TextAndPhotoRepository textAndPhotoRepository;

    public TextAndPhotoService(TextAndPhotoRepository textAndPhotoRepository) {
        this.textAndPhotoRepository = textAndPhotoRepository;
    }

    public TextPhotoBasedEntity addEntity(TextPhotoBasedEntity entity){
        return textAndPhotoRepository.addEntityTy(entity);
    }

    public TextPhotoBasedEntity getEntityById(String entityId) throws EntityNotFoundException {
        return textAndPhotoRepository.getEntityById(entityId);
    }
}
