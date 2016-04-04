package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/5.
 */
public class CountingSort {
    public static void main(String[] args){
        int[] array = {2,5,3,0,2,3,0,3};
        CountingSort c = new CountingSort();
        int[] result = c .doCountingSort(array,5);
        for(int i = 0; i < result.length;i++ ){
            System.out.print(result[i]);
        }
    }

    public int[] doCountingSort(int[] a,int k){
        int[] c = new int[k+1];
        int[] b = new int[a.length];
        for(int i = 0; i <= k; i++){
            c[i] = 0;
        }
        for(int j = 0; j < a.length; j++){
            c[a[j]] = c[a[j]] + 1;
        }
        for(int i = 1; i <= k; i++){
            c[i] = c[i] + c[i-1];
         }
        for(int j = a.length-1; j >=0;j--){
            //注意这里与伪代码的区别是要c[a[j]]-1 ----减一要注意
            b[c[a[j]]-1] = a[j];
            c[a[j]] = c[a[j]] - 1;
        }
        return b;
    }

}
