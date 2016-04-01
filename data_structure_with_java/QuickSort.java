package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/2.
 */
public class QuickSort {

    public static void main(String[] args){
        int[] array = {2,8,7,1,3,5,6,4};
        int[] array2 = {2,2,2,2,2,2,2,2,2};
        doQuickSort(array,0,array.length-1);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+" ");
        }
    }

    public static void doQuickSort(int[] array,int begin, int end){

        if(begin < end){
//            int partiionNumber = partitionInc(array, begin, end);
            int partitionDec = partitionDec(array,begin,end);
//            System.out.println(partiionNumber);
            doQuickSort(array, begin, partitionDec - 1);
            doQuickSort(array,partitionDec+1,end);
        }
    }

    public static int partitionInc(int[] array, int begin, int end){
        int x = array[end];
        int i = begin -1;
        for(int j = begin; j < end; j++){
            if(array[j] <= x){
                i = i+1;
                int temp = 0;
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp2 = 0;
        temp2 = array[end];
        array[end] = array[i+1];
        array[i+1] =temp2;
        if(i == end-1){
            return (begin+end)/2;
        }
        return i+1;

    }

    public static int partitionDec(int[] array, int begin, int end){
        int x = array[end];
        int i = begin -1;
        for(int j = begin; j < end; j++){
            if(array[j] >= x){
                i = i+1;
                int temp = 0;
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp2 = 0;
        temp2 = array[end];
        array[end] = array[i+1];
        array[i+1] =temp2;
        if(i == end-1){
            return (begin+end)/2;
        }
        return i+1;

    }

}




