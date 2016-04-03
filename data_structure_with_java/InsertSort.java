package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/4.
 */
public class InsertSort {

    public static void main(String[] args){

        int[] array = {5,2,4,6,1,3};
        doInsertSort(array);
        ArrayUtils.printArray(array);
    }

    public static void doInsertSort(int[] array){
        int key = 0;
        int j = 0;
        for(int i = 1; i < array.length; i++){
            key = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > key){
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
        }
    }
}
