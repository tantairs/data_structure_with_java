package com.lianxi.data_structure_with_java.stringmatch;

import java.util.Scanner;

/**
 * Created by tantairs on 9/28/16.
 */
public class Main {


    public static String print(String from, int index) {
        from = reverse(from);
        String first = reverse(from.substring(0, from.length() - index));
        String second = reverse(from.substring(from.length() - index));
        from = first + second;
        return from;
    }

    public static String reverse(String from) {
        char[] froms = from.toCharArray();
        for (int i = 0; i < from.length() / 2; i++) {
            char temp = froms[i];
            froms[i] = froms[from.length() - 1 - i];
            froms[froms.length - 1 - i] = temp;
        }
        return String.valueOf(froms);
    }


    public static int findMaxMatch(String string){

        int count = 0;
        for(int i = 1; i <= string.length(); i++){
           String result = print(string,i);
            if(result.equals(string))
                count++;
        }

        return count;
    }

    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        String string = cin.nextLine();

        int result = findMaxMatch(string);
        System.out.println(result);

    }


}
