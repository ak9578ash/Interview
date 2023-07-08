package com.interview.preparation.low_level_design.ecomm_product_review.model.review;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Meta {
    private String metaId;
    private String imagePath;

    public Meta(String imagePath){
        this.metaId = UUID.randomUUID().toString();
        this.imagePath = imagePath;
    }
}
