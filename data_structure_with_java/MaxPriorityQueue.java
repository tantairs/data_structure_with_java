//package com.lianxi.data_structure_with_java;
//
///**
// * Created by tantairs on 16/4/2.
// */
//public class MaxPriorityQueue {
//
//    public static void main(String[] args){
//        int[] array = {16,14,10,8,7,9,3,2,4,1};
//        MaxPriorityQueue maxPriorityQueue = new MaxPriorityQueue();
//        maxPriorityQueue.buildMaxHeap(array);
////        System.out.println(maxPriorityQueue.heapMaximum(array));
////        int maxValue = maxPriorityQueue.heapExtractMax(array,array.length);
////        System.out.println(maxValue);
//        maxPriorityQueue.heapIncreaseKey(array,8,15);
//        for(int i = 0; i < array.length; i++){
//            System.out.print(array[i] + " ");
//        }
//    }
//
//    private final int DEFAULT_CAPACITY_VALUE = 16;
//    private int capacity = DEFAULT_CAPACITY_VALUE;
//    private int[] array = new int[capacity];
//
//    private int heapSize = 0;
//
//    public void insert(int value){
//        array[heapSize] =  value;
//        heapSize++;
//
//    }
//
//    //调整堆,使堆保持堆序性质
//    public void maxHeapify(int[] array,int heapSize, int index){
//        //因为数组的下标为0,所以这里要特殊处理一下!!!!!
//        int leftIndex = index*2+1;
//        int rightIndex = index*2+2;
//        int largestIndex = 0;
//
//        if(leftIndex < heapSize && array[leftIndex] > array[index]){
//            largestIndex = leftIndex;
//        }else {
//            largestIndex = index;
//        }
//        if(rightIndex < heapSize && array[rightIndex] > array[largestIndex]){
//            largestIndex = rightIndex;
//        }
//        if(largestIndex != index){
//            int temp = 0;
//            temp = array[largestIndex];
//            array[largestIndex] = array[index];
//            array[index] = temp;
//            maxHeapify(array,heapSize,largestIndex);
//        }
//    }
//
//    //构建一个最大堆
//    public void buildMaxHeap(int[] array){
//        for(int i = array.length/2-1; i >=0; i--){
//            maxHeapify(array,array.length,i);
//        }
//    }
//
//    //返回堆中具有最大键字
//    public int heapMaximum(){
//        return array[0];
//    }
//
//    //去掉并返回堆中具有最大键值的元素
//    public int heapExtractMax(int[] array, int heapSize){
//        int max = 0;
//        if(heapSize < 1){
//            System.out.println("heap underflow");
//            return -1;
//        }
//
//        max = array[0];
//        array[0] = array[heapSize-1];
//        maxHeapify(array,heapSize-1 ,0);
//        return max;
//    }
//
//
//    public void heapIncreaseKey(int index,int key){
//        if(key < array[index-1]){
//            System.out.println("new key is smaller than current key");
//            return;
//        }
//        array[index-1] = key;
//        while (index > 0 && array[index/2-1] < key){
//            int temp = 0;
//            temp = key;
//            key = array[index/2-1];
//            array[index/2-1] = temp;
//            index = index/2;
//        }
//    }
//
//    public void maxHeapInsert(int[] array,int index,int heapSize){
//        heapSize =heapSize+1;
//        array[heapSize-1] = -10000000;
//        heapIncreaseKey(array,heapSize,index);
//    }
//}
