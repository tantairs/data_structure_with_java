package com.lianxi.tongji;

/**
 * Created by tantairs on 16/3/25.
 */
public class exe1_1 {
    public static void main(String[] args){
        replaceSpace(new StringBuffer("app aaa aaaa aaa"));
    }

    public static String replaceSpace(StringBuffer str) {
        String temp = null;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                temp += "%20";
            }
            temp += String.valueOf(str.charAt(i));
        }
        System.out.println(temp);
        return temp;

    }
}
