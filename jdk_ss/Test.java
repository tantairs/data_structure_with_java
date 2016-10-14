package com.lianxi.jdk_ss;

import java.util.HashMap;

/**
 * Created by tantairs on 8/3/16.
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("1", "tantaris");
        String nameString = hashMap.get(0);
        System.out.println(nameString);
    }
}
