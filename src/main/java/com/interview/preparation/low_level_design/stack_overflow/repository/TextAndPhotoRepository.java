package com.interview.preparation.low_level_design.stack_overflow.repository;

import com.interview.preparation.low_level_design.stack_overflow.exception.EntityNotFoundException;
import com.interview.preparation.low_level_design.stack_overflow.model.TextPhotoBasedEntity;

import java.util.HashMap;
import java.util.Map;

public class TextAndPhotoRepository {
    Map<String , TextPhotoBasedEntity> textPhotoBasedEntityMap = new HashMap<>();

    public TextPhotoBasedEntity addEntityTy(TextPhotoBasedEntity entity){
        textPhotoBasedEntityMap.putIfAbsent(entity.getId() , entity);
        return entity;
    }

    public TextPhotoBasedEntity getEntityById(String entityId) throws EntityNotFoundException {
        TextPhotoBasedEntity entity = textPhotoBasedEntityMap.get(entityId);
        if(entity==null){
            throw new EntityNotFoundException("provided entity is not valid");
        }
        return entity;
    }

}
