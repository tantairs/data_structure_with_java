package com.lianxi.java_core_exercise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by tantairs on 15/12/15.
 */
public class ProxyTest {
    public static void main(String[] args){
        Object[] emlents = new Object[1000];
        for(int i = 0; i <emlents.length; i++){
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class},handler);
            emlents[i] = proxy;
        }
        Integer key = new Random().nextInt(emlents.length) + 1;
        int result = Arrays.binarySearch(emlents,key);
        if(result >= 0) System.out.println(emlents[result]);
    }
}
