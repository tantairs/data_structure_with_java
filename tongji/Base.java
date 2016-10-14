package com.lianxi.tongji;

/**
 * Created by tantairs on 10/14/16.
 */
public class Base {

    public void printTest(){
        System.out.println("Base");
    }

    public static void main(String[] args){
        Child child = new Child();
        Base base = (Base)child;
        base.printTest();
    }

}

class Child extends Base{

    public void printTest(){
        System.out.println("Child");
    }

}