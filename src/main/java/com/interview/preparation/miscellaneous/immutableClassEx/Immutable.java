package com.interview.preparation.miscellaneous.immutableClassEx;

public final class Immutable {
    private final int i;
    private final int[]data;

    public Immutable(int i,int []values){
        this.i = i;
        this.data = new int[values.length]; // deep copy
        for(int j=0;j<values.length;j++){
            data[j] = values[j];
        }
    }

    public int getI(){
        return i;
    }
    public int[] getData(){
        return this.data.clone(); // returning the clone not the original
    }
}
