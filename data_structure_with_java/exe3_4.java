package com.lianxi.data_structure_with_java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tantairs on 16/2/26.
 */
public class exe3_4 {
    public static void main(String[] args){
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(3);
        arrayList1.add(5);
        arrayList1.add(7);
        arrayList1.add(10);
        arrayList2.add(2);
        arrayList2.add(4);
        arrayList2.add(6);
        arrayList2.add(8);
        arrayList2.add(10);

        List list1 = bingJi(arrayList1,arrayList2);
        Iterator iterator1 = list1.iterator();
        while (iterator1.hasNext())
            System.out.print(iterator1.next() + ", ");

        System.out.println();
        System.out.println("------------------------");
        List list2 = jiaoJi(arrayList1,arrayList2);
        Iterator iterator2 = list2.iterator();
        while (iterator2.hasNext())
            System.out.print(iterator2.next() + ", ");
    }

    public static List bingJi(List l1,List l2){
        List temp = new ArrayList<>();
        temp.addAll(l1);
        Iterator iterator = l2.iterator();
        while (iterator.hasNext()){
            int a = (Integer)iterator.next();
            if(!l1.contains(a))
                temp.add(a);
        }
        return temp;
    }
    public static List jiaoJi(List l1,List l2){
        List temp = new ArrayList<>();
        Iterator iterator = l2.iterator();
        while(iterator.hasNext()){
            int a = (Integer)iterator.next();
            if(l1.contains(a))
                temp.add(a);
        }
        return temp;
    }
}
