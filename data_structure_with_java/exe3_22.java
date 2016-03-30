package com.lianxi.data_structure_with_java;

import java.util.Stack;

/**
 * Created by tantairs on 16/3/3.
 */
public class exe3_22 {
    public static void main(String[] args){
        float result = evalPostFix();
        System.out.println(result);
    }

    public static float evalPostFix(){
        Stack<Float> stack = new Stack<Float>();
        boolean isNumber;
        float a = 0;
        float x = 0;
        float y = 0;
        String str = "6523+8*+3+*";
        for(int i = 0; i < str.length();i++){
            char c = str.charAt(i);
            try {
                isNumber = true;
                a = Float.parseFloat(c + "");

            }catch (Exception e){
                isNumber = false;
            }
            if(isNumber)
                stack.push(a);
            else {
                switch (c){
                    case '+': x = stack.pop(); y = stack.pop();
                        stack.push(x+y);
                        break;
                    case '-': x = stack.pop(); y = stack.pop();
                        stack.push(x-y);
                        break;
                    case '*': x = stack.pop(); y = stack.pop();
                        stack.push(x * y);
                        break;
                    case '/': x = stack.pop(); y = stack.pop();
                        stack.push(x / y);
                        break;
                }
            }

        }
        return stack.peek();
    }
}
