package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/9.
 */
public class PalinderemeJudge {
    public static void main(String[] args){

        String str = "level";
        int result = isPalindereme(str,0,str.length());
        System.out.println(result);
    }

    public static int isPalindereme(String str,int begin,int end){
        int n = end - begin + 1;
        if(n < 0)
            return 0;
        if(n == 0 || n == 1)
            return 1;
        return str.charAt(begin) == str.charAt(end-1) ? isPalindereme(str.substring(begin,end),begin+1,end-1):0;
    }
}
