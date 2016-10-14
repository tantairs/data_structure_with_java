package com.lianxi.tongji;

/**
 * Created by tantairs on 9/12/16.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        if(a <= 1000 && a >= 1 && b <= 1000 && b >=1){
            int tempA = revNumber(a) + revNumber(b);
            int tempB = revNumber(tempA);



            System.out.println(tempB);
        }

    }

    public static int revNumber(int number){

        int result = 0;
        int temp = number;
        if( temp < 10){
            result = temp;
        }else if(temp >= 10 && temp < 100){
            String aa = "";
            if(temp % 10 != 0){
                aa += temp % 10;
            }
            aa += temp/10;
            result = Integer.parseInt(aa);

        }else if(temp >= 100 && temp < 1000){
            String bb = "";
            if(temp % 10 != 0){
                bb += temp % 10;
            }
            int cc = temp % 100;
            if(cc != 0){
                bb += cc / 10;

            }
            bb += temp / 100;
            result = Integer.parseInt(bb);

        }else if(temp == 1000){
            result = 1;
        }else if(temp > 1000){
            String bb = "";
            if(temp % 10 != 0){
                bb += temp % 10;
            }
            int cc = temp % 100;
            if(cc != 0){
                bb += cc / 10;

            }
            int dd = temp % 1000;
            if(dd != 0){
                bb += dd / 100;
            }
            bb += temp / 1000;
            result = Integer.parseInt(bb);
        }

        return result;

    }

}