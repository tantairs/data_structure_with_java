package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 16/3/23.
 */
public class UnsynchBankTest {

    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args){
        Bank b = new Bank(NACCOUNTS,INITIAL_BALANCE);
        for(int i = 0; i < NACCOUNTS; i++){
            TransferRunnable r = new TransferRunnable(b,i,INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }

}
