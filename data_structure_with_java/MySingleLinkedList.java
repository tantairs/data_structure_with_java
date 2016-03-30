package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/2/29.
 */
public class MySingleLinkedList<AnyType> {

    private int theSize;
    private Node<AnyType> beginMaker;

    private static class Node<AnyType>{
        public AnyType data;
        public Node<AnyType> next;
        public Node(AnyType d, Node<AnyType> p){
            data = d;
            next = p;
        }
    }

    //构造并初始化整个单链表 important!!!!!!
    public MySingleLinkedList(){
        clear();
    }

    public void clear(){
        beginMaker = new Node<AnyType>(null,null);
        theSize = 0;
    }

    public int size(){
        return theSize;
    }

    public boolean add(AnyType data){
        add(size(),data);
        return true;
    }

    public void add(int idx, AnyType data){
        addBefore(getNode(idx),data);
    }

    public void addBefore(Node<AnyType> p,AnyType data){
        Node<AnyType> anyTypeNode = new Node<AnyType>(data,p);
        findPrev(p).next = anyTypeNode;
        theSize++;
    }

    public Node<AnyType> findPrev(Node<AnyType> typeNode){
        Node<AnyType> p = beginMaker;
        while (p.next != typeNode){
            p = p.next;
        }
        return p;
    }

    public Node<AnyType> getNode(int idx){
        Node<AnyType> p = beginMaker;
        for(int i = 0; i <= idx; i++){
            p = p.next;
        }
        return p;
    }

    public boolean remove(AnyType data){
        boolean aa = false;
        if(!contains(data)){
            System.out.println("No data is found!");
        }
        Node<AnyType> p = beginMaker;
        while (p.next != null){
            if(p.next.data.equals(data)){
                findPrev(p.next).next = p.next.next;
                --theSize;
                aa = true;
            }
            p = p.next;
        }
        return aa;
    }

    public boolean contains(AnyType data){
        boolean aa = false;
        Node<AnyType> p = beginMaker;
        while (p.next != null){
            if(p.next.data.equals(data))
                aa = true;
            p  = p.next;
        }
        return aa;
    }

    public void printList(){
        Node<AnyType> p = beginMaker;
        while (p.next != null){
            p = p.next;
            System.out.print(p.data + " ");
        }
    }
}
