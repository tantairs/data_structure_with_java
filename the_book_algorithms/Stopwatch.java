package com.lianxi.the_book_algorithms;

/**
 * Created by tantairs on 9/17/16.
 */
public class Stopwatch {

    private final long start;
    public Stopwatch(){
        start = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long now  = System.currentTimeMillis();
        return (now - start)/1000.0;
    }

}
