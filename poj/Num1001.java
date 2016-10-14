package com.lianxi.poj;

import java.util.Scanner;

/**
 * Created by tantairs on 9/13/16.
 */
public class Num1001 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String nextLine = scanner.nextLine();
            String[] data = nextLine.split(" ");
            double RData = Double.parseDouble(data[0]);
            int rData = Integer.parseInt(data[1]);
            System.out.println(caculateExp(RData,rData));
        }
    }

    public static double caculateExp(double R, int r){
        double result = 0;
        for(int i = 0; i < r; i++){
            result *= R;
        }

        return result;
    }

}
