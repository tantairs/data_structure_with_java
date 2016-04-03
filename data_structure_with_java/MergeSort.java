package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/4.
 */
public class MergeSort {

    public static void main(String[] args){
        int[] array = {2,4,5,7,1,2,3,6};
        MergeSort mergeSort = new MergeSort();
        mergeSort.doMergeSort(array,0,array.length-1);
        ArrayUtils.printArray(array);
    }

    public void doMergeSort(int[] array,int p,int r){
        if(p < r){
            int q = (p+r)/2;
            doMergeSort(array,p,q);
            doMergeSort(array,q+1,r);
            merge(array,p,q,r);
        }
    }

    public void merge(int[] array,int p,int q,int r){
        int n1 = q-p+1;
        int n2 = r-q;
        int[] left = new int[n1+1];
        int[] right = new int[n2+1];
        for(int i = 0; i < n1; i++)
            left[i] = array[p+i];
        for(int j = 0; j < n2; j++)
            right[j] = array[q+1+j];
        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for(int k = p; k <= r; k++){
            if(left[i] <= right[j]){
                array[k] = left[i];
                i = i+1;
            }else {
                array[k] = right[j];
                j = j+1;
            }
        }
    }
}
