package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/12/15.
 */
public class ArrayAlg {
    public static class Pair{
        public Pair(double f, double s){
            first = f;
            second = s;
        }

        public double getFirst(){
            return first;
        }

        public double getSecond(){
            return second;
        }
        private double first;
        private double second;
    }

    public static Pair minmax(double[] values){
        double min = 0;
        double max = 0;
        for(double v : values){
            if(min > v) min = v;
            if(max < v) max = v;
        }

        return new Pair(min, max);
    }

}
