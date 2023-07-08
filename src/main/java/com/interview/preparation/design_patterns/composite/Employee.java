package com.interview.preparation.design_patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String dept;
    private String position;

    private List<Employee> subordinates;

    public Employee(String name , String dept , String  position){
        this.name = name;
        this.dept = dept;
        this.position = position;
        this.subordinates = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        this.subordinates.add(employee);
    }

    public void removeEmployee(Employee employee){
        this.subordinates.remove(employee);
    }

    public String getName() {
        return name;
    }

    public List<Employee> getSubordinates(){
        return this.subordinates;
    }

    @Override
    public String toString(){
        return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + position+" ]");
    }

}
