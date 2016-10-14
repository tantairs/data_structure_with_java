package com.lianxi.courseara_datastructure_algorithm;
import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(long[] numbers) {
        long result = 0;
        int n = numbers.length;
        long[] resultArray = doQuickSort(numbers,0,n-1);
        result = (resultArray[0]*resultArray[1]);
        return result;
    }

    public static long[] doQuickSort(long[] array,int begin, int end){

        if(begin < end){
            int partitionDec = partitionDec(array,begin,end);
            doQuickSort(array, begin, partitionDec - 1);
            doQuickSort(array,partitionDec+1,end);
        }
        return array;
    }

    public static int partitionDec(long[] array, int begin, int end){
        long x = array[end];
        int i = begin -1;
        for(int j = begin; j < end; j++){
            if(array[j] >= x){
                i = i+1;
                long temp = 0;
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        long temp2 = 0;
        temp2 = array[end];
        array[end] = array[i+1];
        array[i+1] =temp2;
        if(i == end-1){
            return (begin+end)/2;
        }
        return i+1;

    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}