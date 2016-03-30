package com.lianxi.jvm_exercise;

/**
 * Created by tantairs on 16/3/16.
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args){
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("Stack length: "+ javaVMStackSOF.stackLength);
        }
    }
}
