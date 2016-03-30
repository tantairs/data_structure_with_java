package com.lianxi.jvm_exercise;

/**
 * Created by tantairs on 15/10/5.
 */
public class ReferenceCounting {
    public static void main(String[] args){
        Shared shared = new Shared();
        Composing[] composings = {
                new Composing(shared), new Composing(shared),
                new Composing(shared), new Composing(shared),
                new Composing(shared)
        };
        for(Composing c : composings){
            c.dispose();
        }
    }
}
