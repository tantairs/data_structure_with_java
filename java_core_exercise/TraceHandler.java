package com.lianxi.java_core_exercise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by tantairs on 15/12/15.
 */
public class TraceHandler implements InvocationHandler{

    public TraceHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(object);
        System.out.print("." + method.getName()+ "(");
        if(args != null){
            for(int i = 0; i < args.length; i++){
                System.out.print(args);
                if(i < args.length - 1) System.out.print(", ");
            }
        }
        System.out.println(")");
        return method.invoke(object,args);
    }

    private Object object;
}
