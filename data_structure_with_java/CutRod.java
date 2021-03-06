package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/3.
 */
public class CutRod {

    int[] r = null;
    int[] s = null;
    public static void main(String[] args){
        int[] array = {1,5,8,9,10,17,17,20,24,30};
//        int result = doCutRod(array,10);
        CutRod cutRod = new CutRod();
//        int result = cutRod.doCutRodMemoized(array,10);
//        int result2 = cutRod.doBottomUpCutRod(array,10);
        cutRod.printCutRodSolution(array,10);

//        System.out.println(result+": " + result2);
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
    public int doBottomUpCutRod(int[] price,int n){
        int q = 0;
        r = new int[n+1];
        r[0] = 0;
        for(int j = 1; j <=n; j++){
            q = -10000;
            for(int i = 1; i <=j; i++){
                int temp = price[i-1]+r[j-i];
                if(temp > q)
                    q = temp;
            }
            r[j] = q;
        }
        return r[n];
    }

    //改进版的自底向上的动态规划,使得结果给出切割后每段钢条的长度
    public int doBottomUpCutRodExtended(int[] price,int n){
        int[] result = new int[2];
        int q = 0;
        r = new int[n+1];
        s = new int[n+1];
        r[0] = 0;
        for(int j = 1; j <= n; j++){
            q = -10000;
            for(int i = 1; i <= j; i++){
                if(q < price[i-1]+r[j-i]){
                    q = price[i-1] + r[j-i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }
        return r[n];
    }

    //打印改进版的自底向上的动态规划的结果
    public void printCutRodSolution(int[] price,int n){
        int result = doBottomUpCutRodExtended(price,n);
        System.out.println("输入n为"+ n+"时,最优结果为: "+ result);
        System.out.print("分割结果为: ");
        while (n >0){
            System.out.print(s[n]+" ");
            n = n -s[n];
        }
    }
}
