package com.lianxi.jvm_exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantairs on 16/3/19.
 */
public class OOMObject {

    static class OOMOject2{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void fillHeap(int num) throws InterruptedException{
        List<OOMOject2> list = new ArrayList<OOMOject2>();
        for(int i = 0; i < num; i++){
            Thread.sleep(50);
            list.add(new OOMOject2());
        }
        System.gc();
    }

    public static void main(String[] args){
        try {
            fillHeap(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
