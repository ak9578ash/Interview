package com.interview.preparation.low_level_design.ecomm_product_review.model.summary;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.InvalidRatingException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Summary {
    private String summaryId;
    private String productId;
    private int totalRatings;
    private int totalOneStars;
    private int totalTwoStars;
    private int totalThreeStars;
    private int totalFourStars;
    private int totalFiveStars;

    public Summary(String productId){
        this.summaryId = UUID.randomUUID().toString();
        this.productId = productId;
    }

}
