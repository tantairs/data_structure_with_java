package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/3/31.
 */
public class Test {

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<String,Integer>();
        bst.put("A", 1);
        bst.put("B", 1);
        bst.put("G", 1);
        bst.put("C", 1);
        bst.delete("B");
        bst.print();
    }

}