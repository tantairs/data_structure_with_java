package com.lianxi.data_structure_with_java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantairs on 9/12/16.
 */
public class LCSNative {

    /**
     * 求字符串中所有字符的组合abc --> a,b,c,ab,ac,bc,abc
     * @param str
     */
    public static void bruteForce(String str, String str2){
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= str.length(); i++){
            findSubSequence(str, i, temp,result);
        }
        for(int j = 0; j < result.size(); j++){
            String strTemp = result.get(j);

            int mark1 = 0;

            for(int k = 0; k < str2.length(); k++ ){

                if((str2.charAt(k)+"").equals(strTemp.charAt(mark1)+"")){
                    mark1++;
                }

                if(mark1 == strTemp.length()){
                    System.out.println(strTemp);
                    break;
                }

            }
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
    public static void findSubSequence(String str, int m, List<String> result, List<String> finalResult){
        if(m == 0){
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < result.size(); i++){
                stringBuilder.append(result.get(i));
//                System.out.print(result.get(i));
            }
            finalResult.add(stringBuilder.toString());
//            System.out.println();
            return;
        }

        if(str.length() != 0){
            String first = str.charAt(0) + "";
            result.add(first);
            findSubSequence(str.substring(1,str.length()), m-1, result, finalResult);

            result.remove(result.size()-1);
            findSubSequence(str.substring(1,str.length()), m, result, finalResult);

        }


    }

    public static void main(String[] args){
        bruteForce("ABCBDAB","BDCABA");
    }
}
