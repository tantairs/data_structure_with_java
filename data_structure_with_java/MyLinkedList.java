package com.lianxi.data_structure_with_java;

import java.util.Iterator;

/**
 * Created by tantairs on 16/2/24.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    private static class Node<AnyType>{
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n){
            data = d;
            prev = p;
            next = n;
        }
    }

    public MyLinkedList(){
        clear();
    }

    public void swapSingleList(int i,int j)
    {
        Node<AnyType> addtemp_i = getNode(i-1).next;
        getNode(i-1).next = getNode(i).next;
        getNode(j).prev = getNode(i).prev;
        Node<AnyType> addtemp_jq = getNode(j).next;
        getNode(j).next = addtemp_i;
        getNode(i).prev = getNode(i).next;
        getNode(i).next = addtemp_jq;
        getNode(j+1).prev = addtemp_i;
    }
    public void clear(){
        beginMarker = new Node<AnyType>(null,null,null);
        endMarker = new Node<AnyType>(null,beginMarker,null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx, AnyType x){
        addBefore(getNode(idx),x);
    }

    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public AnyType set(Node<AnyType> p, AnyType newVal){
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx){
        return remove(getNode(idx));
    }

    private void addBefore(Node<AnyType> p, AnyType x){
        Node<AnyType> newNode = new Node<AnyType>(x,p.prev,p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    //exe3.10
    public void removeAll(Iterable<? extends AnyType> items){
        AnyType item, element;
        Iterator<? extends AnyType> iterItems = items.iterator();
        while ( iterItems.hasNext ( ) )
        {
            item = iterItems.next();
            Iterator<? extends AnyType> iterList = iterator();
            while ( iterList.hasNext ( ) )
            {
                element = iterList.next();
                if ( element.equals(item) )
                    iterList.remove();
            }
        }
    }

    //exe3.2 判断链表中是否含有某个数字
    public boolean contains(int data){
        boolean aa = false;
        Node<AnyType> p = beginMarker;
        while (p.next != endMarker){
            int a = (Integer)p.next.data;
            if(a == data){
                aa = true;
                break;
            }
            p = p.next;
        }
        return aa;
    }

    private Node<AnyType> getNode(int idx){
        Node<AnyType> p;
        if(idx < 0 || idx > size())
            throw new IndexOutOfBoundsException();
        if(idx < size() / 2){
            p = beginMarker.next;
            for(int i = 0; i < idx; i++)
                p = p.next;
        }
        else{
            p = endMarker;
            for(int i = size(); i > idx; i--)
                p = p.prev;
        }
        return p;
    }

    public void addFirst(AnyType x){
        addBefore(beginMarker.next,x);
    }

    public void addLast(AnyType x){
        addBefore(endMarker,x);
    }

    public void removeFirst(){
        remove(beginMarker.next);
    }

    public void removeLast(){
        remove(endMarker.prev);
    }

    public AnyType getFist(){
        return get(0);
    }

    public AnyType getLast(){
        return get(size() - 1);
    }

    //exe3.15 遇到问题
//    public void splice(Iterator<AnyType> iterator, MyLinkedList<? extends AnyType> lst){
//        iterator = this.iterator();
//    }


    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    public java.util.ListIterator<AnyType> listIterator()
    {
        return new LinkedListIterator2();
    }

    private class LinkedListIterator implements java.util.Iterator<AnyType>{

        private Node<AnyType> current = beginMarker.next;
        private int expectedModcount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if(modCount != expectedModcount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if(modCount != expectedModcount)
                throw new java.util.ConcurrentModificationException();
            if(!okToRemove)
            throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModcount++;
        }
    }

    private class LinkedListIterator2 implements java.util.ListIterator<AnyType>{

        private Node<AnyType> current = beginMarker.next;
        private int expectedModcurrent = modCount;
        private boolean okToRemove = false;
        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if(modCount != expectedModcurrent)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            AnyType nextItem = current.data;
            current = current.next;
            return nextItem;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != beginMarker;
        }

        @Override
        public AnyType previous() {
            if(modCount != expectedModcurrent)
                throw new java.util.ConcurrentModificationException();
            if(!hasPrevious())
                throw new java.util.NoSuchElementException();
            AnyType prevItem = current.prev.data;
            current = current.prev;
            okToRemove = true;
            return prevItem;
        }

        @Override
        public int nextIndex() {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public void remove() {
            if(modCount != expectedModcurrent)
                throw new java.util.ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
        }

        @Override
        public void set(AnyType anyType) {
            MyLinkedList.this.set(current.next,anyType);
        }

        @Override
        public void add(AnyType anyType) {

            if(modCount != expectedModcurrent)
                throw new java.util.ConcurrentModificationException();
            MyLinkedList.this.addBefore(current.next,anyType);
        }
    }
}
