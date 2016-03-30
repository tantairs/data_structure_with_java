package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/12/12.
 */
public class HashCodeTest {
    public static void main(String[] args){
        String s = "ok";
        char ab = 'o';
        int a = 5 + ab;
        Object o = null;
        o.hashCode();
        System.out.println(a);
        System.out.println(s.hashCode());
    }
}
