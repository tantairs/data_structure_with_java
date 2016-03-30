package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/12/14.
 */
public class Employee implements Comparable<Employee> {

    public Employee(String n,Double s){
        name = n;
        salary = s;
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public int compareTo(Employee other){
        if(salary > other.salary) return 1;
        if(salary < other.salary) return -1;
        return 0;
    }

    private String name;
    private double salary;
}
