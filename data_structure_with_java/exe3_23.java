package com.lianxi.data_structure_with_java;

import java.util.Stack;

/**
 * Created by tantairs on 16/3/3.
 */
public class exe3_23 {

    public static void main(String[] args){
        middleFixToBackFix();
    }

    public static void middleFixToBackFix(){
        String init = "a+b*c+(d*e+f)*g";
        String result = null;

        Stack<Character> stack = new Stack<Character>();


        for(int i = 0; i < init.length(); i++){
            char current = init.charAt(i);

            if(current >= 'a' && current <= 'z')
                System.out.print(current+ " ");
            else{
                switch (current){
                    case ')':
                        while ( !stack.isEmpty()&& stack.peek() !='('){
                            System.out.print(stack.pop()+" ");
                        }
                        stack.pop();
                        break;
                    case '(':
                        stack.push(current);
                        break;
                    case '*':
                    case '/':
                        while (!stack.empty() && stack.peek() != '+'&& stack.peek() != '-'
                                && stack.peek() !='('){
                            System.out.print(stack.pop());
                        }
                        stack.push(current);
                        break;
                    case '+':
                    case '-':
                        while (!stack.empty() && stack.peek() !='('){
                            System.out.print(stack.pop()+" ");
                        }
                        stack.push(current);
                        break;
                }
            }

        }
        while (!stack.empty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();

    }

    public static void backToMiddleFix(){

    }
}
