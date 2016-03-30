package com.lianxi.java_core_exercise;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by tantairs on 15/12/16.
 */
public class LinkedListTest {
    public static void main(String[] args){
        List<String> list = new LinkedList<String>();
        list.add("Amy");
        list.add("jack");
        list.add("dick");

        List<String> list1 = new LinkedList<String>();
        list1.add("aa");
        list1.add("bb");
        list1.add("cc");

        ListIterator<String> aIter = list.listIterator();
        Iterator<String> bIter = list1.iterator();

        while (bIter.hasNext()){
            if(aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(list);

        bIter = list1.iterator();
        while (bIter.hasNext()){
            bIter.next();
            if(bIter.hasNext()){
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(list1);

        list.removeAll(list1);

        System.out.println(list);
    }
}
