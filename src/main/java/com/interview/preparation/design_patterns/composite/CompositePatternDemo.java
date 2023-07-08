package com.interview.preparation.design_patterns.composite;

public class CompositePatternDemo {
    public static void main(String[] args) {
        Employee ceo = new Employee("Kabeer Viswas","org","CEO");
        Employee doe1 = new Employee("Yashash Uday Shankar","eng","DOE");
        Employee doe2 = new Employee("Varun Khurana","eng","DOE");

        ceo.addEmployee(doe1);
        ceo.addEmployee(doe2);

        Employee em = new Employee("Laxmi Verma","eng","EM");
        doe1.addEmployee(em);

        System.out.println(ceo.getName());
        for (Employee headEmployee : ceo.getSubordinates()){
            System.out.println(headEmployee.getName());
            for (Employee subordinate : headEmployee.getSubordinates()){
                System.out.println(subordinate);
            }
        }
    }

}
