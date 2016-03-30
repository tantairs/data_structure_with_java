package com.lianxi.data_structure_with_java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by tantairs on 16/2/26.
 */
public class exe3_6 {
    public static void main(String[] args){
        solveJP(2,5);
    }

    public static void solveJP(int m, int n){
        List list1 = new ArrayList<>();
        List list2 = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            list1.add(i);
        }

        int record = 0;
        int people = n;

        while(people >1){
            if(record+m%people < people){
                list1.remove(record+m%people);
                record = record+m%people;
            }else {
                list1.remove((record+m%people)%people);
                record = (record+m%people)%people;
            }
            people--;
        }
        Iterator iterator = list1.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
