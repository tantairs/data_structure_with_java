package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 16/4/1.
 */
public class EnumOrder {
    public static void main(String[] args){
        for(Spiciness s : Spiciness.values())
            System.out.println(s + ", ordinal " + s.ordinal());
    }
}
