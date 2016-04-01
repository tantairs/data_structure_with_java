package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/2.
 */
public class BubbleSort {
    public static void main(String[] args){
        int[] arrayList = {3,1,4,9,5,7};
        sort(arrayList);
        for(int i = 0; i < arrayList.length; i++){
            System.out.print(arrayList[i]);
        }
    }

    public static void sort(int[] array){
        for(int i = 0; i < array.length-1;i++){
            for(int j = array.length-1; j > i; j--){
                if(array[j-1] > array[j]){
                    int temp = 0;
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
