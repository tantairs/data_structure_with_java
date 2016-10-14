package com.lianxi.data_structure_with_java;

import java.util.Scanner;

/**
 * Created by tantairs on 16/5/23.
 */
public class Fib {
    private static long getFibonacciLastDigit(int n) {
        long[] arrays = new long[n + 1];
        if (n <= 1)
            return n;
        else {
            arrays[0] = 0;
            arrays[1] = 1;
            for (int i = 2; i <= n; i++) {
                arrays[i] = (arrays[i - 1] + arrays[i - 2])%10;
            }
        }
        return arrays[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}
