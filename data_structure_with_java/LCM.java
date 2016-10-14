package com.lianxi.data_structure_with_java;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by tantairs on 16/5/23.
 */
public class LCM {
    private static BigInteger lcm(int a, int b) {
        int aa = a;
        int bb = b;
        BigInteger aNumber = BigInteger.valueOf(aa);
        BigInteger bNumber = BigInteger.valueOf(bb);
        BigInteger product = aNumber.multiply(bNumber);
        BigInteger gcdNumber = BigInteger.valueOf(gcd(a, b));
        BigInteger lcm = product.divide(gcdNumber);
        return lcm;
    }

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

        System.out.println(lcm(a, b));
    }
}
