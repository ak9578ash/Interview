package com.interview.preparation.miscellaneous.shallowDeepCopyEx;

import lombok.Getter;

@Getter
public class DeepClass {
    private final int[]data;

    public DeepClass(int[] values) {
        data = new int[values.length];

        for(int i=0;i<values.length;i++){
            data[i] = values[i];
        }
    }
}
