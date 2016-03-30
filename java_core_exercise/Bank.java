package com.lianxi.java_core_exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tantairs on 16/3/23.
 */
public class Bank {

    private final double[] accounts ;
    private Lock bankLock = new ReentrantLock();
    public Bank(int n, double initialBalance){
        accounts = new double[n];
        for(int i = 0; i < accounts.length; i++){
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount){

        bankLock.lock();
        try{
            if(accounts[from] <  amount) return;
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d", amount,from,to);
            accounts[to] += amount;
            System.out.printf("Total Balance: %10.2f%n", getTotalBalance());

        }
        finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance(){
        double sum = 0;

        for(double a: accounts){
            sum += a;
        }
        return sum;
    }

    public int size(){
        return accounts.length;
    }
}
