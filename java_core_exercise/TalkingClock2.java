package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by tantairs on 15/12/15.
 */
public class TalkingClock2 {
   public void start(int interval, final boolean beep){
       ActionListener listener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Date date = new Date();
               System.out.println("the time is " + date);
               if(beep) Toolkit.getDefaultToolkit().beep();
           }
       };

       Timer timer = new Timer(interval,listener);
       timer.start();
   }
}
