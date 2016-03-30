package com.lianxi.java_core_exercise;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by tantairs on 15/12/17.
 */
public class SetTest {
    public static void main(String[] args){
        Set<String> words = new HashSet<String>();
        long totalTime = 0;

        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String word = in.next();
            long callTime = System.currentTimeMillis();
            words.add(word);
            totalTime += callTime;
        }
        Iterator<String> iterator = words.iterator();
        for(int i = 1; i <= 20 && iterator.hasNext(); i++){
            System.out.println(iterator.next());
        }
        System.out.println("...");
        System.out.println(words.size() + " distinct words." + totalTime + " milliseconds");
    }
}
