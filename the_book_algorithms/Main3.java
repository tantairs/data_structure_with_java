package com.lianxi.the_book_algorithms;

import java.util.Scanner;

/**
 * Created by tantairs on 9/18/16.
 */
public class Main3 {

    public static int caculateTheNumberOfZero(int number){
        int count = 0;
        int k;

        for (int i = 5;i <= number;i += 5){
            k = i;
            while (k % 5 == 0){
                count++;
                k = k / 5;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int result = caculateTheNumberOfZero(a);
        System.out.println(result);

    }
}
