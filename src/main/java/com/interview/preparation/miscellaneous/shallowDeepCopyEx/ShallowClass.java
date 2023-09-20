package com.interview.preparation.miscellaneous.shallowDeepCopyEx;

import lombok.Getter;

@Getter
public class ShallowClass {
    private final int[] data;

    public ShallowClass(int[] values){
        this.data = values;
    }

}
