package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tantairs on 9/6/16.
 */
public class ClassLoaderTest {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ClassLoaderFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
