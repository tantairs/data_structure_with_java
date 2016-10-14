package com.lianxi.data_structure_with_java;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tantairs on 8/10/16.
 */
public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node{
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key){
        Node x = get(root,key,0);
        if(x == null) return null;
        return (Value)x.val;
    }

    private Node get(Node x, String key, int d){
        if(x == null) return null;
        if(d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c],key,d+1);
    }

    public void put(String key, Value val){
        root = put(root,key,val,0);
    }

    private Node put(Node x, String key, Value val, int d){
        if(x == null) x = new Node();
        if(d == key.length()){
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c],key,val,d+1);
        return x;
    }

    public Iterable<String> keys(){
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre){
        Queue<String> q = new LinkedList<>();
        collect(get(root,pre,0),pre,q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q){
        if(x == null) return;
        if(x.val != null) {
            q.add(pre);
        }
        for(char c = 0; c < R; c++){
            collect(x.next[c],pre+c,q);
        }
    }

    public Iterable<String> keysThatMatch(String pat){
        Queue<String> q = new LinkedList<>();
        collect(root,"",pat,q);
        return q;
    }

    private void collect(Node x, String pre, String pat, Queue<String> q){
        int d = pre.length();
        if(x == null) return;
        if(d == pat.length() && x.val != null){
            q.add(pre);
        }
        if(d == pat.length()) return;
        char next = pat.charAt(d);
        for(char c = 0; c < R; c++){
            if(next == '.' || next == c){
                collect(x.next[c],pre+c,pat,q);
            }
        }

    }

    public String longestPrefixOf(String s){
        int length = search(root,s,0,0);
        return s.substring(0,length);
    }

    private int search(Node x, String s, int d, int length){
        if(x == null) return length;
        if(x.val != null) length = d;
        if(d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c],s,d+1,length);
    }


    public static void main(String[] args){
        TrieST<Integer> trieST = new TrieST<>();
        trieST.put("apple",4);
        trieST.put("sh",2);
        trieST.put("he",1);
        trieST.put("hello",0);
        trieST.put("it",3);
        trieST.put("hi",5);
//        System.out.println(trieST.get("姚明"));
        System.out.println(trieST.keysThatMatch(".."));

        System.out.println(trieST.keysWithPrefix("he"));
//        System.out.println(trieST.longestPrefixOf("知不知道姚明"));
    }
}
