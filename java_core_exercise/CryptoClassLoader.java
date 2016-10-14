package com.lianxi.java_core_exercise;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by tantairs on 9/6/16.
 */
public class CryptoClassLoader extends ClassLoader{
    public CryptoClassLoader(int k){
        key = k;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        try{
            classBytes = loadClassBytes(name);
        }catch (IOException e){
            throw new ClassNotFoundException(name);
        }

        Class<?> cl = defineClass(name,classBytes,0,classBytes.length);
        if(cl == null) throw new ClassNotFoundException(name);
        return cl;
    }

    private byte[] loadClassBytes(String name) throws IOException{
        String cname = name.replace('.','/') + ".caesar";
        FileInputStream in = null;
        in = new FileInputStream(cname);
        try {
            ByteOutputStream buffer = new ByteOutputStream();
            int ch;
            while ((ch = in.read()) != -1){
                byte b = (byte)(ch - key);
                buffer.write(b);
            }
            in.close();
            return buffer.toByteArray();
        }finally {
            in.close();
        }
    }

    private int key;
}
