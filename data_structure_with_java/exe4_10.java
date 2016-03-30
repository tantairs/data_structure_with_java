package com.lianxi.data_structure_with_java;

import java.io.File;

/**
 * Created by tantairs on 16/3/13.
 */
public class exe4_10 {
    public static void main(String[] args){
        exe4_10 exe = new exe4_10();
        exe.list(new File("/Users/tantairs"));

    }

    public void list(File file){
        list(file,0);
    }

    public void list(File file, int depth){
        printFiles(file,depth);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File i : files){
                list(i, depth+1);
            }
        }
    }

    public void printFiles(File file, int depth){
        String name = file.getName();
        for(int i = 0; i < depth; i++){
            System.out.print(" ");
        }
        if(file.isDirectory()){
            System.out.println("Dir: " + name);
        }
        else
            System.out.println(file.getName() + " " + file.length());
    }
}
