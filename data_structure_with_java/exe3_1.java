package com.lianxi.data_structure_with_java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tantairs on 16/2/26.
 */
public class exe3_1 {
    public static void main(String[] args){
        ArrayList L = new ArrayList();
        ArrayList P = new ArrayList();
        L.add(33);
        L.add(23);
        L.add(45);
        L.add(34);
        L.add(56);
        L.add(57);
        L.add(98);
        L.add(22);
        P.add(1);
        P.add(3);
        P.add(4);
        P.add(6);
        Iterator iterator = L.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("************");

        printLots(L, P);
    }

    public static void printLots(List L, List P){
        Iterator iterator = P.iterator();
        while (iterator.hasNext()){
            int a = (Integer)iterator.next();
            System.out.println(L.get(a));
        }
    }
}
