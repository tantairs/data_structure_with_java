package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 8/31/16.
 */
public class TrieSTForMe {
    private static int R = 256;
    private Node root;

    static class Node{
        private int value;
        Node[] nodes = new Node[R];
    }

    public int get(String key){
        Node x = get(root,key,0);
        if (x != null){
            return x.value;
        }else {
          return -1;
        }
    }

    private Node get(Node x, String key, int d){
        if(x == null) return null;
        if(d == key.length()){
            return x;
        }
        char c = key.charAt(d);
        return get(x.nodes[c],key,d+1);
    }

    public void put(String key, int value){
        root = put(root,key,value,0);
    }

    private Node put(Node x, String key, int value, int d){
        if (x == null) x = new Node();
        if (d == key.length()){
            x.value = value;
            return x;
        }
        char c = key.charAt(d);
        x.nodes[c] = put(x.nodes[c],key,value,d+1);
        return x;
    }

    public static void main(String[] args){
        TrieSTForMe trieSTForMe = new TrieSTForMe();
        trieSTForMe.put("she",1);
        trieSTForMe.put("sh",2);
        trieSTForMe.put("he",3);
        System.out.println(trieSTForMe.get("sah"));
    }

}
