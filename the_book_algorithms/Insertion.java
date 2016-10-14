package com.lianxi.the_book_algorithms;

/**
 * Created by tantairs on 9/17/16.
 */
public class Insertion extends Sorting {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for(int i = 1; i < N; i++){
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
                exch(a,j,j-1);
            }
        }

    }

    public static void main(String[] args){

        String[] a = {"bed","bug","yes","zoo","all","bad","yet"};
        Insertion insertion = new Insertion();
        insertion.sort(a);
        insertion.show(a);
    }

}
