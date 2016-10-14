package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/7.
 */
public class KmpMatcher {


    public static void doKmpMatcher(String t, String p){
        int n = t.length();
        int m = p.length();
        int[] pi = computePrefixFunction(p);
        int q = 0;
        for(int i = 0; i < n; i++ ){
          while (q > 0 && p.charAt(q+1) != t.charAt(i)){
              q = pi[q];
          }
            if(p.charAt(q+1) == t.charAt(i))
                q++;
            if(q == m-1){
                for(int j = 0; j < i - m; j++){
                    System.out.print(" ");
                }
                for(int k = 0; k < m; k++){
                    System.out.print(p.charAt(k));
                }
                q = pi[q];
            }

        }
    }

    public static int[] computePrefixFunction(String p){
        int m = p.length();
        int[] pi = new int[m];
        pi[0] = -1;
        int k = -1;
        for(int i = 1; i < m; i++){
            while(k > -1 && p.charAt(k+1) != p.charAt(i)){
                k = pi[k];
            }

            if(p.charAt(k+1) == p.charAt(i)){
                k++;
            }
            pi[i] = k;
        }
        return pi;
    }

    public static void main(String[] args){
        System.out.println("bacbababaabcbab");
        doKmpMatcher("bacbababaabcbab", "ababa");
    }
}
