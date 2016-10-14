package com.lianxi.the_beautity_of_programming;

/**
 * Created by tantairs on 10/8/16.
 */
public class FindMaxMin_2_10 {
    public static void main(String[] args){
        int[] array = {5,6,8,3,7,9};
        int[] result = findMaxMin(array);
        System.out.println(result[0]+":" + result[1]);

    }

    public static int[] findMaxMin(int[] array){
        for(int i = 0; i < array.length; i+=2){
            if(array[i] < array[i+1]){
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
        int[] result = new int[2];
        result[0] = Integer.MIN_VALUE;
        result[1] = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i+=2){
            if(array[i] > result[0]){
                result[0] = array[i];
            }
            if(array[i+1] < result[1]){
                result[1] = array[i+1];
            }
        }
        return result;
    }


}
