package com.lianxi.data_structure_with_java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantairs on 9/13/16.
 */
public class TotalSubSequence {

    /**
     * 求字符串中所有字符的组合abc --> a,b,c,ab,ac,bc,abc
     * @param str
     */
    public static void findSubSequence(String str){
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= str.length(); i++){
            findSubSequence(str, i, result);
        }
    }

    /**
     * 思路:
     * 要找一个字符串长度为n的所有子串,设子串的长度为m,则如果取字符串第一个字符,则需要在剩下的n-1个字符里面取m-1个
     * 如果不取第一个字符,则需要在剩下的n-1个字符里面取m个.
     * 边界条件为:
     * 1.如果m=0,则说明不取子串.
     * 2.如果字符串为空,则说明取完或没有了.
     * @param str
     * @param m
     * @param result
     */
    public static void findSubSequence(String str, int m, List<String> result){
        if(m == 0){
            for(int i = 0; i < result.size(); i++){
                System.out.print(result.get(i));
            }
            System.out.println();
            return;
        }

        if(str.length() != 0){
            String first = str.charAt(0) + "";
            result.add(first);
            findSubSequence(str.substring(1,str.length()), m-1, result);

            result.remove(result.size()-1);
            findSubSequence(str.substring(1,str.length()), m, result);

        }


    }

    public static void main(String[] args){
        findSubSequence("ABCBDAB");
    }

}
