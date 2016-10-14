package com.lianxi.the_book_algorithms;

import java.util.Scanner;

/**
 * Created by tantairs on 9/17/16.
 */
public class Main {

    public static int caculateMaxProfit(String[] values){
        int N = values.length;
        int max = 0;
        for(int i = 0; i < N-1; i++){
            for(int j = i+1; j < N; j++){
                int temp = Integer.parseInt(values[j])-Integer.parseInt(values[i]);
                if(temp > max){
                    max = temp;
                }
            }
        }
        return max;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numbers = line.split(",");
        int result = caculateMaxProfit(numbers);
        System.out.println(result);
    }

}
