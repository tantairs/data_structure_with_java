package com.lianxi.data_structure_with_java;

import java.util.Scanner;

/**
 * Created by tantairs on 16/5/23.
 */
public class GCD {
    private static int gcd(int a, int b) {
        int temp = a % b;
        if(temp == 0)
            return b;
        else{
            return gcd(b,temp);
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(gcd(a, b));
    }

}
