package com.lianxi.jvm_exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantairs on 15/10/13.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args)  {

        List<String> list = new ArrayList<String>();

        int i = 0;

        while (true){
            list.add(String.valueOf(i++).intern());
            System.out.println(i);
        }

    }
}
