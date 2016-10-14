package com.lianxi.thinking_in_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tantairs on 10/6/16.
 */
public class FillingLists {

    public static void main(String[] args){
        List<StringAddress> list = new ArrayList<>(Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
    }
}

 class StringAddress{
    private String s;
    public StringAddress(String s){
        this.s = s;
    }
    public String toString(){
        return super.toString()+ "" + s;
    }
}