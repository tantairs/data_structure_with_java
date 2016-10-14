package com.lianxi.data_structure_with_java;

import java.util.*;

/**
 * Created by tantairs on 8/21/16.
 */
public class HuaWeiTest {
//    public static int caculateTheLengthOfLastWord(String string){
//        String[] str = string.split(" ");
//        int N = str[str.length-1].length();
//        return N;
//    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s = "";
//        while(input.hasNextLine()){
            s = input.nextLine();
//        }
        String result = caculateTheNextSecondTime(s);
//        Set<String> set = new HashSet<>();
//        List<String> list = new ArrayList<>();
//        for(int i = 0; i < s.trim().length(); i++){
//            if(!set.contains(String.valueOf(s.charAt(i)))){
//                set.add(String.valueOf(s.charAt(i)));
//                list.add(String.valueOf(s.charAt(i)));
//            }
//
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for(String str : list){
//            stringBuilder.append(str);
//        }
        System.out.println(result);

//        int N = caculateTheLengthOfLastWord(s);
//        System.out.println(N);
//        String str = "nhrwlbcc8m7c5hih9mhalw98k0322wf2jjm47kk3ntm9snfrflzzundn7d608usy049asxalzjk7izj6amcqhr8uubc04g52mcjboj2fmge2l6iarizfu4yve5o4i3srf5zgqbg82ckcotdeqp760mc9gzei5dzk5gj9x9yj05o3hle0ii64krkkp5i7blh7nbu3gu5vgi2scyn4yqx3z4vcjbyzhnqkh887izotjkg2l0mit0k14vyn39";
//        char c = 't';
//
//        int N = caculateTheNumberOfCharacterInString(str,c);
//        System.out.println(N);
    }

//    public static int caculateTheNumberOfCharacterInString(String str, char c){
//        int N = 0;
//        for(int i = 0; i < str.length(); i++){
//            if(Math.abs(str.charAt(i)-c) == 32||str.charAt(i)-c == 0){
//                N++;
//            }
//        }
//        return N;
//    }

    public static String caculateTheNextSecondTime(String beginTime){

        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        String[] twoHalf = beginTime.split(" ");
        String firstPart = twoHalf[0];
        String nextPart = twoHalf[1];
        String[] firstPartSplit = firstPart.split("/");
        String[] nextPartSplit = nextPart.split(":");
        year = Integer.parseInt(firstPartSplit[0]);
        month = Integer.parseInt(firstPartSplit[1]);
        day = Integer.parseInt(firstPartSplit[2]);
        hour = Integer.parseInt(nextPartSplit[0]);
        minute = Integer.parseInt(nextPartSplit[1]);
        second = Integer.parseInt(nextPartSplit[2]);

        boolean isLeapYear = isLeapYear(year);
        if(second == 59){
            second = 1;
            if(minute == 59){
                minute = 1;
                if(hour == 23){
                    hour = 0;
                    if(day == caculateDaysForEachMonth(month,isLeapYear)){
                        day =1;
                        if(month == 12){
                            month = 1;
                            year++;
                        }else {
                            month++;
                        }
                    }else {
                        day++;
                    }
                }else{
                    hour++;
                }
            }else {
                minute++;
            }

        }else {
            second++;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(year)).append("/");
        if(month < 10){
            stringBuilder.append("0").append(String.valueOf(month)).append("/");
        }else {
            stringBuilder.append(String.valueOf(month)).append("/");
        }
        if(day < 10){
            stringBuilder.append("0").append(String.valueOf(day)).append(" ");
        }else{
            stringBuilder.append(String.valueOf(day)).append(" ");
        }

        if(hour < 10){
            stringBuilder.append("0").append(String.valueOf(hour)).append(":");
        }else {
            stringBuilder.append(String.valueOf(day)).append(":");
        }
        if(minute < 10){
            stringBuilder.append("0").append(String.valueOf(minute)).append(":");
        }else {
            stringBuilder.append(String.valueOf(minute)).append(":");
        }
        if(second < 10){
            stringBuilder.append("0").append(String.valueOf(second));
        }else {
            stringBuilder.append(String.valueOf(second));
        }

        return stringBuilder.toString();
    }

    public static boolean isLeapYear(int year){
        if(year % 400 == 0 || year % 4 == 0 && year % 100  != 0){
            return true;
        }else {
            return false;
        }
    }

    public static int caculateDaysForEachMonth(int month, boolean isLeapYear){
        int dayCount = 0;
        if(month == 1){
            dayCount = 31;
        }else if (month == 2){
            if(isLeapYear){
                dayCount = 29;
            }else {
                dayCount = 28;
            }
        }else if(month == 3){
            dayCount = 31;
        }else if(month == 4){
            dayCount = 30;
        }else if(month == 5){
            dayCount = 31;
        }else if(month == 6){
            dayCount = 30;
        }else if(month == 7){
            dayCount = 31;
        }else if(month == 8){
            dayCount = 31;
        }else if(month == 9){
            dayCount = 30;
        }else if(month == 10){
            dayCount = 31;
        }else if(month == 11){
            dayCount = 31;
        }else if(month == 12){
            dayCount = 31;
        }
        return dayCount;
    }

}
