package com.lianxi.tongji;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tantairs on 16/2/28.
 */
public class Test {
    int value = 0;

    public static void main(String[] args){
//        int[] a = new int[10];
//        int coloum = 91;
//        Random r = new Random();
//        int randomCol = r.nextInt(coloum-1)+1;
//        System.out.println(randomCol);
//
//
//        for(int i = 1; i <= 5;i++) {
//            a[i] = i;
//        }
//
//        for(int i = 0; i <= 5;i++){
//            System.out.print(a[i] + " ");
//        }

//        ArrayList[] arrayLists = new ArrayList[2];
//        arrayLists[0].add(1);
//        arrayLists[0].add(2);

//        System.out.println(arrayLists.length);

//        HashMap hashMap = new HashMap();
//        hashMap.put(1,"a");
//        hashMap.put(1,"b");
//        System.out.println(hashMap.values());

//        sum();

//        String ss = "169";
//        Float.parseFloat(ss);
//        Set<String> s = new HashSet<String>();
//        Collections.addAll(s,args);
//        System.out.println(s);
//        StringBuffer stringBuffer = new StringBuffer();
//        Test test = new Test();
//        System.out.println("bpple".compareTo("apple"));
//        Test test = new Test();
//        double result = test.solveHighFuction(1.0776,10);
//        System.out.println(test.func(1.15));

//        HashSet set = new HashSet();
//        set.add("a");
//        set.add("b");
//        set.add("c");
//        boolean bl = set.contains("c");
//        System.out.println(bl);
        String str1 = "甲型病毒性肝炎";
        String str2 = "病毒";
        StringBuilder stringBuilder = new StringBuilder();
        ClassLoader.getSystemClassLoader();
//
//        HashMap hashMap = new HashMap();
//        System.out.print(str1.contains(str2));
        while (true){
            System.out.println("");
        }
//        System.out.println(str2.lastIndexOf(str1));

    }

    public double solveHighFuction(double x1, double x2){
        double mid;
        while (x1 <= x2){
            mid = (x1+x2)/2;
            if(func(mid) == 0)
                return mid;
            if(func(mid) > 0)
               x2 = mid;
            if(func(mid) < 0)
                x1 = mid;
        }
        return -1;
    }
    public double func(double x){
        double y = Math.pow(x,4)-1.4368*Math.pow(x,3)+0.4368;
        return y;
    }

    public static void sum(){
        Long sum = 0l;
        for(long i = 0; i < Integer.MAX_VALUE; i++){
            sum += i;
        }
        System.out.println(sum);
    }

    public static int hash(String key, int tableSize){
        int hashVal = 0;
        for(int i = 0; i < key.length(); i++){
            hashVal += key.charAt(i);
        }
        return hashVal % tableSize;
    }

    public int Fibonacci(int n) {

        if(n==1||n==2){
            return 1;
        }
        value = Fibonacci(n-2)+Fibonacci(n-1);
        return value;
    }
}
