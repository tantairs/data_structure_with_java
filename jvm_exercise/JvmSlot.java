package com.lianxi.jvm_exercise;

/**
 * Created by tantairs on 6/19/16.
 */
public class JvmSlot {

    public static void main(String[] args){
        {
            byte[] placeholder = new byte[64*1024*1024];
        }

        int a = 0;
        System.gc();
    }
}
