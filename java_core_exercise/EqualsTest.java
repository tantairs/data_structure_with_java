package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/12/12.
 */
public class EqualsTest {

    public static void main(String[] args){
        Object o1 = new String("a");
        Object o2 = new String("a");
//        o1.equals(o2);
        String a = new String("ok");
        String b = new String("ok");

        String a1 = "";
        String a2 = "";

        System.out.println(a == b);
        System.out.println(a1.equals(a2)+ "-----");
        System.out.println(a.equals(b));
        if(o1 instanceof String){
            System.out.println("o1 是 String 类型");
        }else {
            System.out.println("o2 不是 String 类型");
        }
        System.out.println(o1.equals(o2));
    }
}
