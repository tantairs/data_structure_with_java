package com.lianxi.java_core_exercise;

import javax.swing.*;

/**
 * Created by tantairs on 15/12/15.
 */
public class InnerClassTest {
    public static void main(String[] args){
        TalkingClock clock = new TalkingClock(1000,true);
        clock.start();

        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}
