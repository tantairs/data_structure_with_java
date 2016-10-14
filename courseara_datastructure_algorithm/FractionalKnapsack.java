package com.lianxi.courseara_datastructure_algorithm;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by tantairs on 16/5/27.
 */
public class FractionalKnapsack {

    private double getOptimalValue(int capacity, int[] values, int[] weights, int n) {
        double value = 0;
        TreeMap<Float,Integer> treeMap = new TreeMap(new Sort());
        //write your code here
        for(int i = 0; i < n; i++){
            float temp = (float)values[i]/weights[i];
            treeMap.put(temp,i);
        }

        Iterator iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()){
            float key = (float)iterator.next();
            int value_1 = treeMap.get(key);
            if(capacity <= 0){
                break;
            }else {
                if(weights[value_1] <= capacity){
                    capacity -= weights[value_1];
                    value += values[value_1];
                }else {
                    value = value + (float)values[value_1]/weights[value_1]*capacity;
                    capacity = 0;
                }

            }
        }

        return value;
    }

    private class Sort implements Comparator<Float>{
        @Override
        public int compare(Float o1, Float o2) {
            return o2.compareTo(o1);
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        FractionalKnapsack fractionalKnapsack = new FractionalKnapsack();
        System.out.println(fractionalKnapsack.getOptimalValue(capacity, values, weights, n));
    }
}
