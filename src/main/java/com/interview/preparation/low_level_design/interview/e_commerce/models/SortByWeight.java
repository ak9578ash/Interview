package com.interview.preparation.low_level_design.interview.e_commerce.models;

import java.util.Comparator;
import java.util.Objects;

public class SortByWeight implements Comparator<Product>{
    @Override
    public int compare(Product product1, Product product2) {
        // Compare by sponsoredWeight in decreasing order
        if(product1.getSponsoredWeight() > product2.getSponsoredWeight()){
            return -1;
        }else if(Objects.equals(product1.getSponsoredWeight(), product2.getSponsoredWeight())){
            return 0;
        }else {
            return 1;
        }
    }
}
