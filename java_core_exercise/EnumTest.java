package com.lianxi.java_core_exercise;

import java.util.Scanner;

/**
 * Created by tantairs on 15/12/13.
 */
public class EnumTest {
    public static void main(String[] args){
        System.out.println(Size.SMALL.toString());
        System.out.println(Size.EXTRA_LARGE.toString());

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a size:(SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class,null);
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.getAbbreviation());
        if(size == Size.EXTRA_LARGE){
            System.out.println("Good job--you paid attention to the _.");
        }
    }

}
