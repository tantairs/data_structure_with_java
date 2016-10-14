package com.lianxi.courseara_datastructure_algorithm;
import java.util.Scanner;

public class APlusB {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        while (!(a >= 0 && b <= 9)){
            Scanner ss = new Scanner(System.in);
            a = ss.nextInt();
            b = ss.nextInt();
        }
        System.out.println(a + b);
    }
}