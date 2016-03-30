package com.lianxi.jvm_exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantairs on 15/10/13.
 */
public class HeapOOM {

    static class OOMObject{

    }
    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
