package com.lianxi.java_core_exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

/**
 * Created by tantairs on 9/6/16.
 */
public class ClassLoaderFrame extends JFrame {

    public ClassLoaderFrame(){
        setTitle("ClassLoaderTest");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        add(new JLabel("Class"));
        add(namefield);
        add(new JLabel("Key"));
        add(keyField);
        JButton loadButton = new JButton("load");
        add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runClass(namefield.getText(),keyField.getText());
            }
        });
        pack();

    }

    public void runClass(String name, String key){
        try{
            ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("main",String[].class);
            m.invoke(null,(Object)new String[]{});
        }catch (Throwable e){
            JOptionPane.showMessageDialog(this,e);
        }
    }

    private JTextField keyField = new JTextField("3",4);
    private JTextField namefield = new JTextField("Calculator",30);
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

}
