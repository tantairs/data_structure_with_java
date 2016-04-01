package com.lianxi.data_structure_with_java;

import com.sun.xml.internal.xsom.impl.WildcardImpl;

/**
 * Created by tantairs on 16/4/1.
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
    public BinaryHeap(){

    }

    public BinaryHeap(int capacity){

    }

    public BinaryHeap(AnyType[] items){

    }

    public void insert(AnyType x){

    }

    public AnyType findMin(){

        return null;
    }

    public AnyType deleteMin(){

        return null;
    }

    public boolean isEmpty(){

        return false;
    }

    public void makeEmpty(){

    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;

    private AnyType[] array;

    private void percolateDown(int hole){

    }
    private void buildHeap(){

    }
    private void enlargeArray(int newSize){

    }
}
