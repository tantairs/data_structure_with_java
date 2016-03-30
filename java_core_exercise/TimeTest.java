package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by tantairs on 15/12/15.
 */
public class TimeTest {
    public static void main(String[] args){
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(1000,listener);
        t.start();

        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}
