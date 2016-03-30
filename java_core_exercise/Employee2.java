package com.lianxi.java_core_exercise;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tantairs on 15/12/14.
 */
public class Employee2 implements Cloneable {
    public Employee2(String n, double s){
        name = n;
        salary = s;
        hireDay = new Date();
    }

    public Employee2 clone() throws CloneNotSupportedException{
        Employee2 cloned = (Employee2)super.clone();
        cloned.hireDay = (Date)hireDay.clone();
        return cloned;
    }

    public void setHireDay(int year, int month, int day){
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString(){
        return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
    private String name;
    private double salary;
    private Date hireDay;
}
