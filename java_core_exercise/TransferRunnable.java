package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 16/3/23.
 */
public class TransferRunnable implements Runnable{

    private int fromAccount;
    private double maxAccount;
    private int DELAY = 10;
    private Bank bank;

    public TransferRunnable(Bank b, int from, double max){
        bank = b;
        fromAccount = from;
        maxAccount = max;
    }

    @Override
    public void run() {
        try{
            while (true){
                int toAccount = (int)(bank.size()*Math.random());
                double amount = maxAccount * Math.random();
                bank.transfer(fromAccount,toAccount,amount);
                Thread.sleep((int)(DELAY* Math.random()));
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
