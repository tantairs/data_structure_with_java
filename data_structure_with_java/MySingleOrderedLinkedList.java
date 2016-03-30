package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/2/29.
 */
public class MySingleOrderedLinkedList {
    private int theSize;
    private Node<Comparable> beginMaker;

    private static class Node<Comparable>{
        public Comparable data;
        public Node<Comparable> next;

        Node(){
            this(null,null);
        }
        Node(Comparable d){
            this(d,null);
        }
        public Node(Comparable d, Node<Comparable> p){
            data = d;
            next = p;
        }
    }

    //构造并初始化整个单链表 important!!!!!!
    public MySingleOrderedLinkedList(){
        clear();
    }

    public void clear(){
        beginMaker = new Node<Comparable>();
        beginMaker.next = null;
        theSize = 0;
    }

    public int size(){
        return theSize;
    }

    public boolean add(Comparable data){

        if(contains(data)){
            return false;
        }else {
            Node<Comparable> p = beginMaker.next;
            Node<Comparable> trailer = beginMaker;
            while (p != null && p.data.compareTo(data) < 0){
                trailer = p;
                p = p.next;
            }
            trailer.next = new Node<Comparable>(data);
            trailer.next.next = p;
            theSize++;
        }
        return true;
    }



//    public void addWithOrderInc(AnyType data){
//        Node<AnyType> p = beginMaker;
//        while (p.next != null){
//            if(p.next.data.compareTo(data))
//        }
//    }

//    public Node<AnyType> findPrev(Node<AnyType> typeNode){
//        Node<AnyType> p = beginMaker;
//        while (p.next != typeNode){
//            p = p.next;
//        }
//        return p;
//    }

//    public Node<AnyType> getNode(int idx){
//        Node<AnyType> p = beginMaker;
//        for(int i = 0; i <= idx; i++){
//            p = p.next;
//        }
//        return p;
//    }

    public boolean remove(Comparable data){

        if(!contains(data)){
            return false;
        }
        Node<Comparable> p = beginMaker.next;
        Node<Comparable> trailer = beginMaker;
        while (!p.data.equals(data)){
            trailer = p;
            p = p.next;
        }
        trailer.next = p.next;
        theSize--;
        return true;
    }

    public boolean contains(Comparable data){
        boolean aa = false;
        Node<Comparable> p = beginMaker;
        while (p.next != null){
            if(p.next.data.equals(data))
                aa = true;
            p  = p.next;
        }
        return aa;
    }

    public void printList(){
        Node<Comparable> p = beginMaker;
        while (p.next != null){
            p = p.next;
            System.out.print(p.data + " ");
        }
    }

}
