package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 9/13/16.
 */
public class Fibnaci {

    public static void main(String[] args){
        Fibnaci fibnaci = new Fibnaci();
        long start = System.currentTimeMillis();
//        long a = getFibnaci(50);

        long a = fibnaci.getFibnaci2(50);
        long end = System.currentTimeMillis();
        System.out.println(a + " and the time consumed is: " + (end - start));
    }

    public static int getFibnaci(int n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        return getFibnaci(n - 1) + getFibnaci(n - 2);
    }

    public long getFibnaci2(int n){
        long[] array = new long[n+1];
        array[0] = 0;
        array[1] = 1;

        for(int i = 2; i <= n; i++){
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }
}
