package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Created by tantairs on 16/3/17.
 */
public class Bounce {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
