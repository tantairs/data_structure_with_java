package com.lianxi.java_core_exercise;

import javax.swing.*;

/**
 * Created by tantairs on 15/12/15.
 */
public class AnoymousInnerClassTest {
    public static void main(String[] args){
        TalkingClock2 talkingClock2 = new TalkingClock2();
        talkingClock2.start(1000,true);

        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}
