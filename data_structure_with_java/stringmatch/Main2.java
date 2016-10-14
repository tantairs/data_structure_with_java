package com.lianxi.data_structure_with_java.stringmatch;

import java.util.Scanner;

/**
 * Created by tantairs on 9/28/16.
 */
public class Main2 {

    public static void main(String[] args) {
        char buf[] = { '1', '2','3' };

        perm(buf, 0, buf.length - 1);
    }

    public static void perm(char[] buf, int start, int end) {
        if (start == end) {
            for (int i = 0; i <= end; i++) {
                System.out.print(buf[i]);
            }
            System.out.println();
        } else {// 多个字母全排列
            for (int i = start; i <= end; i++) {
                char temp = buf[start];// 交换数组第一个元素与后续的元素
                buf[start] = buf[i];
                buf[i] = temp;

                perm(buf, start + 1, end);// 后续元素递归全排列

                temp = buf[start];// 将交换后的数组还原
                buf[start] = buf[i];
                buf[i] = temp;
            }
        }
    }
//    public static int getTheMOne(int n, int m){
//
//        int[] init = new int[n];
//        for(int i = 0; i < n; i++){
//            init[i] = i+1;
//        }
//
//
//        return 0;
//    }

//    public static void main(String args[])
//    {
//        Scanner cin = new Scanner(System.in);
//        int n = cin.nextInt();
//        int m = cin.nextInt();
//        int result = getTheMOne(n,m);
//
//        System.out.println();
//
//    }

}
