package com.lianxi.data_structure_with_java;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by tantairs on 16/2/24.
 */
public class TestMain {
    public static void main(String[] args){
//        MyArrayList myArrayList = new MyArrayList();
//        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.add(1);
//        myLinkedList.add(2);
//        myLinkedList.add(3);
//        myArrayList.add(5);
//        myArrayList.add(6);
//        myArrayList.add(7);
//        myArrayList.add(8);
//        myArrayList.add(9);
//        myArrayList.add(10);
//        myArrayList.add(12);
//        MyArrayList myArrayList1 = new MyArrayList();
//        myArrayList1.addAll(myArrayList);
//        myLinkedList.removeAll(myLinkedList);
//        System.out.println(myArrayList.size());
//        ListIterator<Integer> iterator = myArrayList.listIterator();
//        iterator.add(555);
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

//        MySingleLinkedList mySingleLinkedList = new MySingleLinkedList();
//        mySingleLinkedList.add(1);
//        mySingleLinkedList.add(2);
//        mySingleLinkedList.add(8);
//        mySingleLinkedList.add(5);
//        mySingleLinkedList.add(3);
//        mySingleLinkedList.printList();
//        mySingleLinkedList.remove(1);
//        System.out.println();
//        System.out.println(mySingleLinkedList.size() + "  " + mySingleLinkedList.contains(1));
//        mySingleLinkedList.printList();

//        MySingleOrderedLinkedList mySingleOrderedLinkedList = new MySingleOrderedLinkedList();
//        mySingleOrderedLinkedList.add(1);
//        mySingleOrderedLinkedList.add(9);
//        mySingleOrderedLinkedList.add(8);
//        mySingleOrderedLinkedList.add(5);
//        mySingleOrderedLinkedList.add(0);
//        mySingleOrderedLinkedList.printList();

//        System.out.print("aaaa");
//        MyTreeSet<Integer> myTreeSet = new MyTreeSet<>();
//        myTreeSet.insert(6);
//        myTreeSet.insert(2);
//        myTreeSet.insert(1);
//        myTreeSet.insert(4);
//        myTreeSet.insert(3);
//        myTreeSet.insert(8);
//        myTreeSet.insert(5);
//        myTreeSet.printTree();
//        System.out.println(myTreeSet.findMax());

//        SeparateChainingHashTable separateChainingHashTable = new SeparateChainingHashTable();
//        separateChainingHashTable.insert(4);
//        separateChainingHashTable.insert(155);
//        boolean temp = separateChainingHashTable.contains(3);
//        System.out.println(2+"3");
        int f = 0;
        int g = 1;
        for(int i = 0; i <= 15; i++){
            System.out.println(f);
            f = f + g;
            g = f - g;
        }

    }
}


