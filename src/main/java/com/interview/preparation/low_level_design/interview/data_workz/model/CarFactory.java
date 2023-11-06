package com.interview.preparation.low_level_design.interview.data_workz.model;

import com.interview.preparation.low_level_design.interview.data_workz.Car;
import com.interview.preparation.low_level_design.interview.data_workz.exception.CarNotFoundEcxeption;

public class CarFactory {
    public Car getCar(String carType) throws CarNotFoundEcxeption {
        if(carType=="Sedan"){
            return new Sedan();
        }else if(carType == "Suv"){
            return new Suv();
        }else{
            throw new CarNotFoundEcxeption("provided type does not exist");
        }
    }
}
