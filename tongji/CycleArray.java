package com.lianxi.tongji;

import java.util.Scanner;

/**
 * Created by tantairs on 10/16/16.
 */
public class CycleArray {

    int[] array = {1,2,3,4,5,6,7};
    int N = 3;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);


        CycleArray cycleArray = new CycleArray();
        cycleArray.start();
        cycleArray.print();
    }

    public void print(){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ",");
        }
    }

    public void start(){
        rightMoveN(array,N);
    }

    public void rightMoveN(int[] array, int n){
        exchange(array,0,array.length-n-1);
        exchange(array,array.length-n,array.length-1);
        exchange(array,0,array.length-1);
    }

    public void exchange(int[] array, int a, int b){
        for(int i = a; i <= (a+b)/2; i++){
            int temp = array[i];
            array[i] = array[a+b-i];
            array[a+b-i] = temp;
        }

    }

}
