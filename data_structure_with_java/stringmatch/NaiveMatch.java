package com.lianxi.data_structure_with_java.stringmatch;

/**
 * Created by tantairs on 9/24/16.
 */
public class NaiveMatch {

    public static void naiveStringMatch(String p, String t){

        int n = t.length();
        int m = p.length();

        for(int i = 0; i < n-m; i++){
            int count = 0;
            for(int j = 0; j < m; j++){
                if(p.charAt(j) == t.charAt(i+j)){
                    count++;
                }
            }
            if(count == m){
                for(int z = 0; z < i; z++){
                    System.out.print(" ");
                }
                for(int z = 0; z < m; z++){
                    System.out.print(p.charAt(z));
                }
                System.out.println(" ");
            }
        }

    }

    public static void main(String[] args){
        String t = "acaabc";
        String p = "aab";
        System.out.println(t);
        naiveStringMatch(p, t);
    }

}
