package com.lianxi.jvm_exercise;

import java.sql.Timestamp;

/**
 * Created by tantairs on 15/11/9.
 */
public class TimeConvert {

    public static void main(String[] args){
        Timestamp ts;
        String tsStr = "2011/05/09 11:49:45";
        try {
            ts = Timestamp.valueOf(tsStr);
            System.out.println(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
