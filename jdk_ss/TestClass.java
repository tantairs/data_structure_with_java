package com.lianxi.jdk_ss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by tantairs on 8/3/16.
 */
public class TestClass implements Serializable{

    private static final long serialVersionUID = 0L;
    public TestClass() throws Exception {
        throw new Exception("哎呀妈呀,异常啦!!!!");
    }
    public static void main(String[] args) throws Exception {
        byte[] head = { -84, -19, 0, 5, 115, 114, 0 };
        byte[] ass = { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 120, 112 };
        String name = TestClass.class.getName();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(head);
        baos.write(name.length());
        baos.write(name.getBytes());
        baos.write(ass);
        baos.flush();
        baos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        TestClass o = (TestClass) ois.readObject();
        ois.close();
        System.out.println("创建对象: " + o);
    }
}
