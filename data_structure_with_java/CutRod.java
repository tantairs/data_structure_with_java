package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/3.
 */
public class CutRod {

    public static void main(String[] args){
        int[] array = {1,5,8,9,10,17,17,20,24,30};
        int result = doCutRod(array,10);
        System.out.println(result);
    }
    //利用递归的方法解决钢条分割的问题,注意这里的数组下标与n的取值范围.
    public static int doCutRod(int[] price,int n){
        if(n == 0)
            return 0;
        int q = -10000;
        for(int i = 1; i <= n; i++){
            int temp = price[i-1]+doCutRod(price,n-i);
            if(temp > q)
                q = temp;
        }
        return q;
    }
}
