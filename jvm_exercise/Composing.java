package com.lianxi.jvm_exercise;

import java.util.HashMap;

/**
 * Created by tantairs on 15/10/5.
 */
public class Composing {
    HashMap a = new HashMap();
    private Shared shared;
    private static long counter = 0;
    private final long id = counter++;
    public Composing(Shared shared){
        System.out.println("Creating " + this);
        this.shared = shared;
        this.shared.addRef();
    }
    protected void dispose(){
        System.out.println("disposing " + this);
        shared.dispose();
    }
    @Override
    public String toString(){
        return "Composing " + id;
    }
}
