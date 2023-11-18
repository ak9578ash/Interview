package com.interview.preparation.low_level_design.interview.wayfair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Coupon c1 = new Coupon("Comforter Sets", "Comforters Sale");
        Coupon c2 = new Coupon("Bedding", "Savings on Bedding");
        Coupon c3 = new Coupon("Bed & Bath", "Low price for Bed & Bath");

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(c1);
        coupons.add(c2);
        coupons.add(c3);

        Category cat1 = new Category("Comforter Sets", "Bedding");
        Category cat2 = new Category("Bedding", "Bed & Bath");
        Category cat3 = new Category("Bed & Bath", null);
        Category cat4 = new Category("Soap Dispensers", "Bathroom Accessories");
        Category cat5 = new Category("Bathroom Accessories", "Bed & Bath");
        Category cat6 = new Category("Toy Organizers", "Baby And Kids");
        Category cat7 = new Category("Baby And Kids", null);

        List<Category>categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat4);
        categories.add(cat5);
        categories.add(cat6);
        categories.add(cat7);

        Solution solution = new Solution(coupons, categories);
        System.out.print(solution.getCoupon("Soap Dispensers"));
    }
}
