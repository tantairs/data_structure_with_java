package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by tantairs on 15/12/15.
 */
public class TalkingClock {
    public TalkingClock(int interval, boolean beep){
        this.interval = interval;
        this.beep = beep;
    }

    private int interval;
    private boolean beep;

    public void start(){
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval,listener);
        t.start();
    }

    public class TimePrinter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            System.out.println("the time is " + now);
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}
