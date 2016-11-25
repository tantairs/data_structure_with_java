package com.lianxi.tongji;

/**
 * Created by tantairs on 10/17/16.
 */
public class AbstractTest extends BaseA{
    public AbstractTest(int i){
        super(i);
        i = 5;
    }
}

abstract class BaseA{
    int i = 0;
    public BaseA(int i){
        this.i = i;
    }
}
