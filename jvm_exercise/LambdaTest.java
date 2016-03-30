package com.lianxi.jvm_exercise;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tantairs on 15/12/7.
 */
public class LambdaTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);
        intList.add(11);
        intList.add(12);
        intList.add(13);
        intList.add(14);
        intList.add(15);
        intList.add(16);
        intList.add(17);

        intList.parallelStream().forEach(o->{System.out.println(o);System.out.println(new Date());try {
            Thread.sleep(10000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }});

    }



}
