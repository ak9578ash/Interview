package com.interview.preparation.low_level_design.solid_principle.open_and_close.model;

public class Marker {
    private String name;
    private  String color;
    private Integer price;

    public Marker(String  name , String  color , Integer price){
        this.name = name ;
        this.color = color;
        this.price  = price;
    }

    public String  getName(){
        return this.name;
    }

    public String  getColor(){
        return this.color;
    }

    public Integer getPrice(){
        return this.price;
    }
}
