package com.lianxi.the_book_algorithms;

/**
 * Created by tantairs on 9/17/16.
 */
public class SortCompare {
    public Sorting sorting;

    public SortCompare(Sorting sorting){
        this.sorting = sorting;
    }

    public double time(Double[] a){
        Stopwatch timer = new Stopwatch();
        sorting.sort(a);
        return timer.elapsedTime();
    }

}
