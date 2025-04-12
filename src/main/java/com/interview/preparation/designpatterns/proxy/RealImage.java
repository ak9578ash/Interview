package com.interview.preparation.designpatterns.proxy;

public class RealImage {
    private String imageName;
    public RealImage(String imageName){
        this.imageName = imageName;
        LoadFromDisk(this.imageName);
    }

    public void Display(){
        System.out.println("Displaying the image");
    }
    private void LoadFromDisk(String  imageName){
        System.out.println("Loading from Disk :" + imageName);

    }
}
