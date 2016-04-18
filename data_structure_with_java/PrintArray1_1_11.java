package com.lianxi.data_structure_with_java;

import java.util.Random;

/**
 * Created by tantairs on 16/4/15.
 */
public class PrintArray1_1_11 {
    public static void main(String[] args){
        boolean[][] a = new boolean[10][10];
        a = initRandom(a);
        printArray(a);


    }

    public static void printArray(boolean[][] a){
        for(int i = 0; i < 10; i++){
            System.out.print(" " + i);
            System.out.println(" ");
            for(int j = 0; j < 10; j++){
                if(a[i][j])
                    System.out.print("*" + " ");
                else
                    System.out.print(" " + " ");
                System.out.println(" ");
            }
        }
    }

    public static boolean[][] initRandom(boolean[][] a){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Random random = new Random(System.currentTimeMillis());
                int result = random.nextInt(10);
                if(result >= 5)
                    a[i][j]= true;
                else {
                    a[i][j]= false;
                }
            }
        }
        return a;
    }
}
