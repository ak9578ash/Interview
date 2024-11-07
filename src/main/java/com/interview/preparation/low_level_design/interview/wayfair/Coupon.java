package com.interview.preparation.low_level_design.interview.wayfair;

/**
 * Given the following data set, find the coupon to display for a given category.
 * <p>
 * Note: Category structure is hierarchical. Categories without coupons inherit their parent’s coupon.
 * <p>
 * # data
 * <p>
 * Coupons = [
 * {"CategoryName":"Comforter Sets", "CouponName":"Comforters Sale"},
 * {"CategoryName":"Bedding", "CouponName":"Savings on Bedding"},
 * {"CategoryName":"Bed & Bath", "CouponName":"Low price for Bed & Bath"}
 * ]
 * <p>
 * Categories = [
 * {"CategoryName":"Comforter Sets", "CategoryParentName":"Bedding"},
 * {"CategoryName":"Bedding", "CategoryParentName":"Bed & Bath"},
 * {"CategoryName":"Bed & Bath", "CategoryParentName":null},
 * {"CategoryName":"Soap Dispensers", "CategoryParentName":"Bathroom Accessories"},
 * {"CategoryName":"Bathroom Accessories", "CategoryParentName":"Bed & Bath"},
 * {"CategoryName":"Toy Organizers", "CategoryParentName":"Baby And Kids"},
 * {"CategoryName":"Baby And Kids", "CategoryParentName":null}
 * ]
 * <p>
 * <p>
 * map[Comforter Sets] = Bedding;
 * map[Bedding] = Bed & Bath
 * <p>
 * <p>
 * # tests: input(CategoryName) => output(CouponName)
 * <p>
 * "Comforter Sets"       => "Comforters Sale"
 * "Bedding"              => "Savings on Bedding"
 * "Bathroom Accessories" => "Low price for Bed & Bath"
 * "Soap Dispensers"      => "Low price for Bed & Bath"
 * "Toy Organizers"       => null
 * <p>
 * <p>
 * <p>
 * <p>
 * >>> Categories can have at most one parent
 * >>> Product can only be associated to one category
 * <p>
 * The system has added a new piece of data to the coupon - “Date Modified”. Use this when resolving any ties.
 * <p>
 * Coupons = [
 * <p>
 * { "CategoryName":"Comforter Sets", "CouponName":"Comforters Sale", "DateModified":"2020-01-01" },
 * { "CategoryName":"Comforter Sets", "CouponName":"Cozy Comforter Coupon", "DateModified":"2020-01-01" },
 * { "CategoryName":"Bedding", "CouponName":"Best Bedding Bargains", "DateModified":"2019-01-01" },
 * { "CategoryName":"Bedding", "CouponName":"Savings on Bedding", "DateModified":"2019-01-01" },
 * { "CategoryName":"Bed & Bath", "CouponName":"Low price for Bed & Bath", "DateModified":"2018-01-01" },
 * { "CategoryName":"Bed & Bath", "CouponName":"Bed & Bath extravaganza", "DateModified":"2019-01-01" },
 * { "CategoryName":"Bed & Bath", "CouponName":"Big Savings for Bed & Bath", "DateModified":"2030-01-01" }
 * <p>
 * ]
 * <p>
 * Example inputs => output
 * <p>
 * "Bed & Bath" => "Bed & Bath extravaganza"
 * <p>
 * [Note that one is in the future which SHOULD NOT be selected]
 * <p>
 * "Bedding" => "Savings on Bedding" | "Best Bedding Bargains"
 * <p>
 * "Bathroom Accessories" => "Bed & Bath extravaganza"
 * <p>
 * "Comforter Sets" => "Comforters Sale" | "Cozy Comforter Coupon"
 **/

class Coupon {
    private final String categoryName;
    private final String couponName;

    public Coupon(String categoryName, String couponName) {
        this.categoryName = categoryName;
        this.couponName = couponName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getCouponName() {
        return this.couponName;
    }
}
