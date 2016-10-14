package com.lianxi.the_book_algorithms;

/**
 * Created by tantairs on 9/17/16.
 */
public class Selection extends Sorting{

    /**
     * 按照 a 的升序进行排序
     * N = a.length
     * 依次扫描第i个到最后一个元素,(i 从 0 ~ N-1),每次从中找到一个最小值依次与第i个元素进行交换
     * @param a
     */
    public void sort(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            int minIndex = i;
            for(int j = i+1; j < N; j++){
                if(less(a[j],a[minIndex])){
                    minIndex = j;
                }
            }
            exch(a,i,minIndex);
        }
    }

    public static void main(String[] args){
        Selection selection = new Selection();
        String[] a = {"bed","bug","yes","zoo","all","bad","yet"};
        selection.sort(a);
        assert selection.isSorted(a);
        selection.show(a);
    }


}
