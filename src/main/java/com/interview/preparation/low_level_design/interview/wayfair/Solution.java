package com.interview.preparation.low_level_design.interview.wayfair;
import java.util.*;
/**
 Given the following data set, find the coupon to display for a given category.

 Note: Category structure is hierarchical. Categories without coupons inherit their parent’s coupon.

 # data

 Coupons = [
 {"CategoryName":"Comforter Sets", "CouponName":"Comforters Sale"},
 {"CategoryName":"Bedding", "CouponName":"Savings on Bedding"},
 {"CategoryName":"Bed & Bath", "CouponName":"Low price for Bed & Bath"}
 ]

 Categories = [
 {"CategoryName":"Comforter Sets", "CategoryParentName":"Bedding"},
 {"CategoryName":"Bedding", "CategoryParentName":"Bed & Bath"},
 {"CategoryName":"Bed & Bath", "CategoryParentName":null},
 {"CategoryName":"Soap Dispensers", "CategoryParentName":"Bathroom Accessories"},
 {"CategoryName":"Bathroom Accessories", "CategoryParentName":"Bed & Bath"},
 {"CategoryName":"Toy Organizers", "CategoryParentName":"Baby And Kids"},
 {"CategoryName":"Baby And Kids", "CategoryParentName":null}
 ]


 map[Comforter Sets] = Bedding;
 map[Bedding] = Bed & Bath


 # tests: input(CategoryName) => output(CouponName)

 "Comforter Sets"       => "Comforters Sale"
 "Bedding"              => "Savings on Bedding"
 "Bathroom Accessories" => "Low price for Bed & Bath"
 "Soap Dispensers"      => "Low price for Bed & Bath"
 "Toy Organizers"       => null




 >>> Categories can have at most one parent
 >>> Product can only be associated to one category

 The system has added a new piece of data to the coupon - “Date Modified”. Use this when resolving any ties.

 Coupons = [

 { "CategoryName":"Comforter Sets", "CouponName":"Comforters Sale", "DateModified":"2020-01-01" },
 { "CategoryName":"Comforter Sets", "CouponName":"Cozy Comforter Coupon", "DateModified":"2020-01-01" },
 { "CategoryName":"Bedding", "CouponName":"Best Bedding Bargains", "DateModified":"2019-01-01" },
 { "CategoryName":"Bedding", "CouponName":"Savings on Bedding", "DateModified":"2019-01-01" },
 { "CategoryName":"Bed & Bath", "CouponName":"Low price for Bed & Bath", "DateModified":"2018-01-01" },
 { "CategoryName":"Bed & Bath", "CouponName":"Bed & Bath extravaganza", "DateModified":"2019-01-01" },
 { "CategoryName":"Bed & Bath", "CouponName":"Big Savings for Bed & Bath", "DateModified":"2030-01-01" }

 ]

 Example inputs => output

 "Bed & Bath" => "Bed & Bath extravaganza"

 [Note that one is in the future which SHOULD NOT be selected]

 "Bedding" => "Savings on Bedding" | "Best Bedding Bargains"

 "Bathroom Accessories" => "Bed & Bath extravaganza"

 "Comforter Sets" => "Comforters Sale" | "Cozy Comforter Coupon"

 **/

class Coupon{
    private final String categoryName;
    private final String couponName;
    public Coupon(String categoryName , String couponName){
        this.categoryName = categoryName;
        this.couponName = couponName;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public String getCouponName(){
        return this.couponName;
    }
}

class Category{
    private final String categoryName ;
    private final String categoryParentName;

    public Category(String categoryName , String categoryParentName ){
        this.categoryName = categoryName;
        this.categoryParentName = categoryParentName;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public String getCategoryParentName(){
        return this.categoryParentName;
    }
}


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
