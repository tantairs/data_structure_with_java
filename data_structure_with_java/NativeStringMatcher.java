package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/7.
 */
public class NativeStringMatcher {

    public static void main(String[] args){
        String str1 = "acaabcc";
        String str2 = "aabc";
        boolean result = findStringMatch(str1,str2);
        System.out.println(result);

    }

    public static boolean findStringMatch(String T, String P){
        boolean temp = false;
        int str1Length = T.length();
        int str2Length = P.length();
        if(str2Length == 0)
            return true;
        for(int i = 0; i < (str1Length - str2Length+1); i++){
            int count = 0;
            for(int j = 0; j < str2Length; j++){
                if(T.charAt(i+j) == P.charAt(j)){
                    count++;
                }
            }
            if(count == str2Length)
                temp = true;
        }

        return temp;
    }

}
