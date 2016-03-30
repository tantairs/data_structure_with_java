package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/11/5.
 */
public class StringTest {

    public String get(String string){
        char[] chars = string.toCharArray();
        return chars[0]+"A";
    }

    public static void main(String[] args){
        StringTest stringTest = new StringTest();
        String a =stringTest.getHint("2345","3415");

        System.out.println(a);
    }

    public String getHint(String secret, String guess){
        int a = 0, b = 0;
        char[] secrets = secret.toCharArray();
        char[] guesses = guess.toCharArray();
        for(int i = 0; i < secret.length(); i++){
            if(secrets[i] == guesses[i]){
                if(i < 1){
                    a++;
                }else {
                    for(int j = i; j< 0; j--){
                        if(guesses[i] != guesses[j]){
                            a++;
                        }
                    }
                }
            }else {
                if(i < 1){
                    b++;
                }else {
                    for(int j = i; j< 0;j--){
                        if(guesses[i] != guesses[j]){
                            b++;
                        }
                    }
                }

            }

        }

        return a + "A" + b + "B";
    }
}
