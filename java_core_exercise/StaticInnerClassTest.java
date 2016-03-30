package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/12/15.
 */
public class StaticInnerClassTest {
    public static void main(String[] args){
        double[] d = new double[20];
        for(int i = 0; i < d.length;i++){
            d[i] = 100 * Math.random();
        }
        ArrayAlg.Pair arrayAlg = ArrayAlg.minmax(d);
        System.out.println(arrayAlg.getFirst());
        System.out.println(arrayAlg.getSecond());
    }
}
