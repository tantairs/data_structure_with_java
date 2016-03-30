package com.lianxi.java_core_exercise;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Created by tantairs on 15/12/13.
 */
public class ReflectionTest {
    public static void main(String[] args) {

        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifies = Modifier.toString(cl.getModifiers());
            if (modifies.length() > 0) System.out.print(modifies + " ");
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class) System.out.print(" extends " + supercl.getName());
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.print("\n}\n");

            // 等待实现

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        /*
        prints all constructors of a class
        @ param cl a class
         */

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");

            //print parameter types
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.print(");");
        }
    }
}

