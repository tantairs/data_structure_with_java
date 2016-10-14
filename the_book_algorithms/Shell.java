package com.lianxi.the_book_algorithms;

/**
 * Created by tantairs on 9/17/16.
 */
public class Shell extends Sorting{
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3 * h + 1;
        while (h >= 1){
            for(int i = h; i < N; i++){
                for(int j = i; j >= h && less(a[j],a[j-h]); j-=h)
                    exch(a,j,j-h);
            }
            h = h/3;
        }
    }

    public static void main(String[] args){
        String[] a = {"bed","bug","yes","zoo","all","bad","yet"};
        Shell shell = new Shell();
        shell.sort(a);
        shell.show(a);
    }
}
