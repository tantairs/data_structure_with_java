package com.lianxi.data_structure_with_java;

import java.util.Scanner;

/**
 * Created by tantairs on 16/4/15.
 */
public class Print1_1_5 {
    public static void main(String[] args){

        double a = 0.0, b = 0.0;
        System.out.println("输入两个double类型的数");
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        boolean result = judgeNumber(a,b);
        System.out.println(result);

    }

    public static boolean judgeNumber(double x, double y){
        if((x <= 1 && x >=0)&&(y <= 1 && y >= 0) )
            return true;
        else {
            return false;
        }
    }
}
