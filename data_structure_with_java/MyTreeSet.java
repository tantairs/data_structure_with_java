package com.lianxi.data_structure_with_java;

import com.sun.xml.internal.xsom.impl.WildcardImpl;

import java.nio.BufferUnderflowException;

/**
 * Created by tantairs on 16/3/13.
 */
public class MyTreeSet<AnyType extends Comparable<? super AnyType>> {

    private BinaryNode<AnyType> root;
    int modCount = 0;

    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt,
                   BinaryNode<AnyType> rt, BinaryNode pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }


        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
        BinaryNode<AnyType> parent;
    }

    public java.util.Iterator<AnyType> iterator(){
        return new MyTreeSetIterator();
    }

    private class MyTreeSetIterator implements java.util.Iterator<AnyType>{

        private BinaryNode<AnyType> current = findMin(root);
        private BinaryNode<AnyType> previous;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        private boolean atEnd = false;

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public AnyType next() {
            if(modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.element;

            previous = current;
            if(current.right != null){
                current = findMin(current.right);
            }
            else {
                BinaryNode<AnyType> child = current;
                current = current.parent;
                while (current != null && current.left != child){
                    child = current;
                    current = current.parent;
                }
                if(current == null)
                    atEnd = true;
            }
            okToRemove = true;

            return nextItem;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            MyTreeSet.this.remove(previous.element);
            okToRemove = false;
        }
    }

    public MyTreeSet(){
        root = null;
    }

    public void makeEmpty(){
        modCount++;
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(AnyType x){
        return contains(x, root);
    }

    public AnyType findMin() throws BufferUnderflowException{
        if(isEmpty())
            throw new BufferUnderflowException();
        else
            return findMin(root).element;
    }

    public AnyType findMax() throws BufferUnderflowException{
        if(isEmpty())
            throw new BufferUnderflowException();
        else
            return findMax(root).element;
    }

    public void insert(AnyType x){
        root = insert(x,root,null);
    }

    public void remove(AnyType x){
        root = remove(x,root);
    }

    public void printTree(){
        if(isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    //先序遍历
    private void printTree(BinaryNode<AnyType> t){
        if(t != null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t){
        if( t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
            return contains(x, t.left);

        else if( compareResult > 0)
            return contains(x, t.right);

        else
            return true; //match
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
        if( t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
        if(t == null)
            return null;
        else if(t.right == null)
            return t;
        return findMax(t.right);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t,
                                       BinaryNode<AnyType> pt){
        if( t == null){
            modCount++;
            return new BinaryNode<AnyType>(x,null,null,pt);
        }

        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            t.left = insert(x,t.left,t);
        else if(compareResult > 0)
            t.right = insert(x, t.right,t);
        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t){
        if(t == null)
            return t;

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
            t.left = remove(x,t.left);
        else if(compareResult > 0)
            t.right = remove(x,t.right);
        else if(t.left != null && t.right != null){
            t.element = findMin(t.right).element;
            t.right = remove(t.element,t.right);
        }
        else {
            modCount++;
            BinaryNode<AnyType> oneChild;
            oneChild = (t.left != null) ? t.left : t.right;
            oneChild.parent = t.parent;
            t = oneChild;
        }
        return t;
    }

}
