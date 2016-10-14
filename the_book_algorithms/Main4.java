package com.lianxi.the_book_algorithms;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by tantairs on 9/18/16.
 */
public class Main4 {

   static class Position{
        public Position(){
        }

        public Position(int row, int col){
            this.col = col;
            this.row = row;
        }

        public String toString(){
            return "(" + row + " ," + col + ")";
        }

        int row;
        int col;
    }


    public static String getResult(int[][] arrays, int n, int m, int pp){

        int row = n;
        int col = m;
        boolean p[][] = new boolean[n][m];
        Stack<Position> stack = new Stack<>();
        int temp[][] = new int[row + 2][col + 2];
        for(int i = 0; i < row + 2; ++i) {
            for(int j = 0; j < col + 2; ++j) {
                temp[0][j] = 1;
                temp[row + 1][j] = 1;
                temp[i][0] = temp[i][col + 1] = 1;
            }
        }
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < col; ++j) {
                temp[i + 1][j + 1] = arrays[i][j];
            }
        }

        int i = 1;
        int j = 1;
        p[i][j] = true;
        stack.push(new Position(i, j));
        while (!stack.empty() && (!(i == (row) && (j == col)))) {

            if ((temp[i][j + 1] == 0) && (p[i][j + 1] == false)) {
                p[i][j + 1] = true;
                stack.push(new Position(i, j + 1));
                j++;
            } else if ((temp[i + 1][j] == 0) && (p[i + 1][j] == false)) {
                p[i + 1][j] = true;
                stack.push(new Position(i + 1, j));
                i++;
            } else if ((temp[i][j - 1] == 0) && (p[i][j - 1] == false)) {
                p[i][j - 1] = true;
                stack.push(new Position(i, j - 1));
                j--;
            } else if ((temp[i - 1][j] == 0) && (p[i - 1][j] == false)) {
                p[i - 1][j] = true;
                stack.push(new Position(i - 1, j));
                i--;
            } else {
                stack.pop();
                if(stack.empty()){
                    break;
                }
                i = stack.peek().row;
                j = stack.peek().col;
            }

        }

        Stack<Position> newPos = new Stack<Position>();
        if (stack.empty()) {
            System.out.println("can not escape!");
        } else {

            while (!stack.empty()) {
                Position pos = new Position();
                pos = stack.pop();
                newPos.push(pos);
            }
        }

       StringBuilder sd = new StringBuilder();
        while (!newPos.empty()) {
            Position p1=newPos.pop();
            sd.append(p1);

        }

        return sd.toString();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();

        int[][] arrays = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arrays[i][j] = sc.nextInt();
            }
        }

        getResult(arrays,n,m,p);

        System.out.println();

    }

}
