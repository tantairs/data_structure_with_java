package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 9/13/16.
 */
public class LCSDP {

    public static void main(String[] args) {
        LCSDP lcsdp = new LCSDP();

        System.out.println(lcsdp.getLCS("ABCBDAB","BDCABA",7,6));

    }


    /**
     * DP 求两个字符串的最长公共子序列的长度.
     *
     * @param str1
     * @param str2
     * @param i : str1的长度
     * @param j : str2的长度
     * @return 两个字符串的最长公共子序列的长度
     */
    public int getLCS(String str1, String str2, int i, int j){
        int common = 0;
        if(i == 0 || j ==0){
            return 0;
        }
        if(str1.charAt(i-1) == str2.charAt(j-1)){
            common = getLCS(str1, str2, i-1, j-1) + 1;
        }else {
            common = max(getLCS(str1,str2, i-1, j), getLCS(str1,str2,i, j-1));
        }
        return common;

    }

    public int max(int a, int b){
        return a >= b ? a : b;
    }

}
