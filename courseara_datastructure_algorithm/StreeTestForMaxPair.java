package com.lianxi.courseara_datastructure_algorithm;

import java.util.Random;

/**
 * Created by tantairs on 16/5/27.
 */
public class StreeTestForMaxPair {
    int max = 100000;
    int min = 0;
    long[] arrays;
    public static void main(String[] args){

        StreeTestForMaxPair streeTestForMaxPair = new StreeTestForMaxPair();
        streeTestForMaxPair.doTest();

    }

    public void doTest(){
        Random random = new Random();
        for(int i = 2; i < 200000; i++){
            arrays = new long[i];

            for(int j = 0; j < i; j++){
                int s = random.nextInt(max)%(max - min+1)+min;
                arrays[j] = s;
            }
            System.out.println("当N= "+ i+" 的时候,跑出的结果");
            System.out.println(MaxPairwiseProduct.getMaxPairwiseProduct(arrays));
        }
    }
}
