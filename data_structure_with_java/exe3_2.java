package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/2/26.
 */
public class exe3_2 {
    public static void main(String[] args){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        boolean a = myLinkedList.contains(7);
        System.out.println(a + "*****");
//        myLinkedList.swapSingleList(3,4);
//        Iterator iterator = myLinkedList.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

    }

    //交换L中位于i,i+1相邻的两个数
    public void swapSingleList(MyLinkedList L, int i, int j){
        //实现在MyLinkedList里面
    }


}
