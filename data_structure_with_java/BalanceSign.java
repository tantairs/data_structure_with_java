package com.lianxi.data_structure_with_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by tantairs on 16/3/3.
 */
public class BalanceSign {
    public static void main(String[] args){
        isBalancedChar();
    }

    public static void isBalancedChar(){
        Stack<Character> stack = new Stack<Character>();
        File file = new File("/Users/tantairs/Downloads/text.txt");
        if(file.isFile()&&file.exists()){
            try{
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"utf8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    for(int i = 0; i < line.length();i++){
                        char c = line.charAt(i);
                        switch (c){
                            case '[':
                                stack.push('[');
                                break;
                            case '{':
                                stack.push('{');
                                break;
                            case '(':
                                stack.push('(');
                                break;
                            case ']':
                            case ')':
                            case '}':
                                if(stack.isEmpty())
                                    System.out.println("sorry," + line.charAt(i) + " is not match!");
                                else {
                                    char cc = stack.pop();
                                    if(c=='('&& cc!=')' || c=='['&& cc != ']' || c =='{'&& cc != '}')
                                        System.out.println("sorry, " + cc + " is not match " + c);
                                }
                                break;
                        }
                    }
                    if(!stack.empty())
                        System.out.println("Error,sign is remaining!");
                    else {
                        System.out.println("match successed!");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
