package com.lianxi.hihocoder;

/**
 * Created by tantairs on 10/29/16.
 */
import java.util.*;
public class Trie_1014 {
    Node root;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numberA = sc.nextInt();
        sc.nextLine();
        int count = 0;
        List<String> words = new ArrayList();
        while (sc.hasNext() && count < numberA){
            words.add(sc.nextLine());
            count++;
        }
        int numberB = sc.nextInt();
        sc.nextLine();
        List<String> wordsPrex = new ArrayList();
        int count2 = 0;
        while(count2 < numberB && sc.hasNext()){
            wordsPrex.add(sc.nextLine());
            count2++;
        }

        Trie_1014 trie_1014 = new Trie_1014();
        trie_1014.buildTrie(words);
        trie_1014.getResult(wordsPrex);


    }

    public void getResult(List<String> arrays){
        for(String str : arrays){
            Node temp = root;
            int length = str.length();
            boolean flag = true;
            for(int i = 0; i < length; i++){
                char c = str.charAt(i);
                if(temp.nextNodes[c-'a'] == null){
                    flag = false;
                }else{
                    temp = temp.nextNodes[c-'a'];
                }
            }
            if(flag){
                System.out.println(temp.count);
            }else{
                System.out.println(0);
            }
        }
    }



    public Node buildTrie(List<String> arrays){
        if(root == null){
            root = new Node();
        }

        for(String word : arrays){
            Node current = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(current.nextNodes[c-'a'] == null){
                    current.nextNodes[c-'a'] = new Node();
                    current.nextNodes[c-'a'].setCount(++current.nextNodes[c-'a'].count);
                    current = current.nextNodes[c-'a'];
                }else{
                    current.nextNodes[c-'a'].setCount(++current.nextNodes[c -'a'].count);
                    current = current.nextNodes[c-'a'];
                }
            }
        }

        return root;

    }

    private class Node{
        int number = 26;
        Node[] nextNodes = new Node[number];
        int count = 0;
        public void setCount(int count){
            this.count = count;
        }
    }
}
