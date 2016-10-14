package com.lianxi.the_book_algorithms;

import java.util.Scanner;

/**
 * Created by tantairs on 9/17/16.
 */
public class Main2 {

    public static int firstPlaceWithANumber(String[] arrays, int count, int value){

        int start = 0;
        int mid = 0;
        int end = count-1;
        int index = -1;

        while (end >= start){
            mid = start + (end-start)/2;
            if(Integer.parseInt(arrays[mid]) == value){
                index = mid;
                break;
            }
            if(Integer.parseInt(arrays[mid]) > value){
                end = mid - 1;
            }
            if(Integer.parseInt(arrays[mid]) < value){
                start = mid + 1;
            }

        }

        if(index == -1){
            return -1 - (mid -1);
        }else {
            while(mid > start)
            {
                if(Integer.parseInt(arrays[(start + end)/2]) != Integer.parseInt(arrays[mid]))
                    start = ((start + mid)/2 + mid + 1)/2;
                else
                    mid = (start + mid)/2;
            }
            return mid;
        }

    }

    public static void main(String[] args){

//        int amount = 0;
        Scanner scanner = new Scanner(System.in);
//        int numberRemainToBeLook = scanner.nextInt();
//        if(scanner.hasNext()){
//            amount = scanner.nextInt();
//        }
//        String line = scanner.nextLine();
//        System.out.println(line);
//        int[] numbers = new int[amount];
//        int count = 0;
//        while (scanner.hasNext()){
//            numbers[count] = scanner.nextInt();
//            count++;
//        }
        String line1 = scanner.nextLine();
        int numberRemainToBeLook = Integer.parseInt(line1);
        String line2 = scanner.nextLine();
        int amount = Integer.parseInt(line2);
        String line3 = scanner.nextLine();
        String lines = line3;
        String[] numbers = lines.split(" ");

        int result = firstPlaceWithANumber(numbers,amount,numberRemainToBeLook);
        System.out.println(result);
    }

}
