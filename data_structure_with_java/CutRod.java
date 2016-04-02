package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/3.
 */
public class CutRod {

    int[] r = null;

    public static void main(String[] args){
        int[] array = {1,5,8,9,10,17,17,20,24,30};
//        int result = doCutRod(array,10);
        CutRod cutRod = new CutRod();
        int result = cutRod.doCutRodMemoized(array,0);

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

    //使用动态规划的自顶向下的过程来求分割
    public  int doCutRodMemoized(int[] price,int n){

        r = new int[n+1];
        for(int i = 0; i <= n; i++){
            r[i] = -10000;
        }
        return memoizedCutRodAux(price,n,r);
    }

    public  int memoizedCutRodAux(int[] price,int n,int[] r){
        int q = -1;
        if(r[n] >= 0)
            return r[n];
        if(n == 0)
            q = 0;
        else {
            q = -10000;
        }
        for(int i = 1; i <= n; i++){
            int temp = price[i-1] + memoizedCutRodAux(price,n-i,r);
            if(q < temp)
                q = temp;
        }
        r[n] = q;
        return q;
    }


    //使用动态规划的自低向上的过程来求解
    public static int doBottomUpCutRod(int[] price,int n){

        return 0;
    }
}
