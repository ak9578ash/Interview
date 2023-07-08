package com.interview.preparation.low_level_design.ecomm_product_review.repository;

import com.interview.preparation.low_level_design.ecomm_product_review.model.summary.Summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummaryRepository {
    public static Map<String , Summary> summaryMap = new HashMap<>();
    public static List<Summary> summaryList = new ArrayList<>();

    public Summary addSummary(Summary summary){
        summaryMap.put(summary.getSummaryId() , summary);
        summaryList.add(summary);
        return summary;
    }

    public Summary getSummaryById(String summaryId){
        return summaryMap.get(summaryId);
    }


}
