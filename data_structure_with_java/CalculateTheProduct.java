package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 10/11/16.
 */
public class CalculateTheProduct {

    public static void main(String[] args){
        double result = getTheProduct(2.0, 7);
        System.out.println(result);
    }

    /**
     * 计算一个数的N次方
     * 采用递归,时间复杂度在 logN
     * @param x
     * @param n
     * @return
     */
    public static double getTheProduct(double x, int n){
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return x;
        }
        if(n == -1){
            return 1/x;
        }
        double result = 0;
        if(n%2 == 0){
            result = getTheProduct(x,n/2);
            result = result*result;
        }else {
            result = getTheProduct(x,n/2);
            result = result*result;
            result = result*x;
        }

        return result;

    }

}
