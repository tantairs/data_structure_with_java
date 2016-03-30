package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tantairs on 16/3/17.
 */
public class BounceFrame extends JFrame {
    private BallComponent comp;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;


   public BounceFrame(){
       setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
       setTitle("Bounce");

       comp = new BallComponent();
       add(comp,BorderLayout.CENTER);
       JPanel buttonPanel = new JPanel();
       addButton(buttonPanel, "Start", new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                addBall();
           }
       });

       addButton(buttonPanel, "Close", new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });
       add(buttonPanel,BorderLayout.SOUTH);
   }



    public void addButton(Container c, String title, ActionListener listener){
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall(){
        try{
            Ball ball = new Ball();
            comp.add(ball);
            for(int i = 1; i < STEPS; i++){
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
