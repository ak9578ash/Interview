package com.interview.preparation.low_level_design.interview.wayfair;
import java.util.*;


public class Solution {
    private final Map<String , String>categoryToCouponMap;
    private final Map<String , String>categoryToParentCategoryMap;

    private  void init(List<Coupon>coupons , List<Category>categories){

        for(int i=0;i<coupons.size();i++){
            categoryToCouponMap.put(coupons.get(i).getCategoryName(),coupons.get(i).getCouponName());
        }

        for(int i=0;i<categories.size();i++){
            String parentCategory = categories.get(i).getCategoryParentName();
            if(parentCategory!=null){
                categoryToParentCategoryMap.put(categories.get(i).getCategoryName() ,categories.get(i).getCategoryParentName());
            }
        }
    }

    public  String getCoupon(String category ){
        if(categoryToCouponMap.containsKey(category)){
            return categoryToCouponMap.get(category);
        }
        if(categoryToParentCategoryMap.containsKey(category)){
            return getCoupon(categoryToParentCategoryMap.get(category));
        }
        return "";
    }

    public Solution(List<Coupon>coupons , List<Category>categories){
        this.categoryToCouponMap = new HashMap<>();
        this.categoryToParentCategoryMap = new HashMap<>();
        init(coupons, categories);
    }
}
