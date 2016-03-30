package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by tantairs on 16/3/17.
 */
public class BallComponent extends JPanel {

    private ArrayList<Ball> balls = new ArrayList<>();

    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(Ball b : balls){
            g2.fill(b.getShape());
        }
    }
}
