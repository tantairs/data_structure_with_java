package com.lianxi.data_structure_with_java;

import java.util.Iterator;

/**
 * Created by tantairs on 16/2/23.
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>  {

    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private AnyType [] theItems;

    public MyArrayList(){
        clear();
    }

    public void clear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType set(int idx,AnyType newVal){
        if( idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize){
            return;
        }
        AnyType [] old = theItems;
        theItems = (AnyType [])new Object[newCapacity];
        for(int i = 0; i < size(); i++){
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx, AnyType x){
        if(theItems.length == size()){
            ensureCapacity(size()*2 + 1);
        }
        for(int i = theSize; i > idx; i--){
            theItems[i] = theItems[i-1];
        }
        theItems[idx] = x;
        theSize++;
    }

    //exe3.9
    public void addAll(Iterable<? extends AnyType> items){
        Iterator item = items.iterator();
        while (item.hasNext()){
            add((AnyType)item.next());
        }
    }

    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];
        for(int i = idx; i < size()-1; i++){
            theItems[i] = theItems[i+1];
        }
        theSize--;
        return removedItem;
    }

    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }



    public java.util.ListIterator<AnyType> listIterator(){
            return new ArrayListIterator2();
    }

    public java.util.Iterator<AnyType> reverseIterator(){
        return new ArrayListReverseIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if( !hasNext())
                throw new java.util.NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public void remove() {
             MyArrayList.this.remove(--current);
        }
    }

    private class ArrayListIterator2 implements java.util.ListIterator<AnyType>{
        private boolean backword = false;
        private int current = 0;
        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            backword = false;
            return theItems[current++];
        }

        @Override
        public boolean hasPrevious() {
            if(current > 0)
                return true;
            return false;
        }

        @Override
        public AnyType previous() {
            if(!hasPrevious())
                throw new java.util.NoSuchElementException();
            backword = true;
            return theItems[--current];
        }

        @Override
        public int nextIndex() {
//            if(!hasNext())
//                throw new java.util.NoSuchElementException();
//            return current++;
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
//            if(!hasPrevious())
//                throw new java.util.NoSuchElementException();
//            return --current;
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public void remove() {
            if(backword == false)
                MyArrayList.this.remove(--current);
            MyArrayList.this.remove(current++);
        }

        @Override
        public void set(AnyType anyType) {
            MyArrayList.this.set(current,anyType);
        }

        @Override
        public void add(AnyType anyType) {
            MyArrayList.this.add(current,anyType);
        }
    }

    private class ArrayListReverseIterator implements java.util.Iterator<AnyType>{
        private int current = size() -1;

        @Override
        public boolean hasNext() {
            return current >= 0;
        }

        @Override
        public AnyType next() {
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            return theItems[current--];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
