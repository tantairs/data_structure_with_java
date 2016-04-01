package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/2.
 */
public class HeapSort {
    public static void main(String[] args){
        int[] arrayList = {4,1,3,2,16,9,10,14,8,7};
        HeapSort heapSort = new HeapSort();
        heapSort.doHeapSort(arrayList);
        for(int i = 0; i < arrayList.length; i++){
            System.out.print(arrayList[i]+ " ");
        }

    }

    public void maxHeapify(int[] array,int heapSize, int index){
        //因为数组的下标为0,所以这里要特殊处理一下!!!!!
        int leftIndex = index*2+1;
        int rightIndex = index*2+2;
        int largestIndex = 0;

        if(leftIndex < heapSize && array[leftIndex] > array[index]){
            largestIndex = leftIndex;
        }else {
            largestIndex = index;
        }
        if(rightIndex < heapSize && array[rightIndex] > array[largestIndex]){
            largestIndex = rightIndex;
        }
        if(largestIndex != index){
            int temp = 0;
            temp = array[largestIndex];
            array[largestIndex] = array[index];
            array[index] = temp;
            maxHeapify(array,heapSize,largestIndex);
        }
    }

    public void buildMaxHeap(int[] array){
        for(int i = array.length/2; i >=0; i--){
            maxHeapify(array,array.length,i);
        }
    }

    public void doHeapSort(int[] array){
        buildMaxHeap(array);
        for(int i = array.length-1; i >=1; i--){
            int temp = 0;
            temp = array[0];
            array[0] = array[i];
            array[0] = temp;
            maxHeapify(array,i,0);
        }
    }
}
