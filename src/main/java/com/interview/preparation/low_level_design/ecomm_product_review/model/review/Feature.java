package com.interview.preparation.low_level_design.ecomm_product_review.model.review;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Feature {
    private String featureId;
    private String featureName;
    private String title;
    private String text;
    private Integer rating;
    private List<Meta> metas;

    public Feature(String featureName , String title , String text ,  Integer rating) {
        this.featureId = UUID.randomUUID().toString();
        this.featureName = featureName;
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.metas = new ArrayList<>();
    }
}
