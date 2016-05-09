package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/5/3.
 */
public class EditDistance {

    /**
     * 存储编辑距离的数组
     */
    int[][] editArray;

    public static void main(String[] args) {
        String[] str1 = {"o","f","a","i","l","i","n","g"};
        String[] str2 = {"o","s","a","i","l","n"};
        EditDistance editDistance = new EditDistance();
        int result = editDistance.doEditDistance(str1,str2);
        System.out.println(result+"-----------------------");
        editDistance.print();
    }


    public void print(){
        for(int i = 0; i < editArray.length; i++){
            for(int j = 0; j <editArray[i].length; j++){
                System.out.print(editArray[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 计算两个字符串的最小编辑距离 delete = 1, insert = 1, substitute = 2
     * 状态转移方程: D(i,j) = min{D(i-1,j)+1,D(i,j-1)+1,D(i-1,j-1)+str1[i]==str2[j] ? 0 : 1}
     * @param str1 "o f a i l i n g"
     * @param str2 "o s a i l n"
     * @return 两个字符串的最小编辑距离 4
     */
    public int doEditDistance(String[] str1, String[] str2) {
        int length1 = str1.length;
        int length2 = str2.length;
        editArray = new int[length2+1][length1+1];
        editArray[0][0] = 0;
        for (int i = 0; i <= length1; i++) {
            editArray[0][i] = i;
        }
        for (int j = 0; j <= length2; j++) {
            editArray[j][0] = j;
        }
        for (int i = 1; i <= length2; i++) {
            for (int z = 1; z <= length1; z++) {
                editArray[i][z] = min(editArray[i][z - 1] + 1, editArray[i - 1][z] + 1, editArray[i - 1][z - 1] + (str1[z-1].equals(str2[i-1]) ? 0 : 1));
            }
        }
        return editArray[length2 - 1][length1 - 1];
    }


    //求出三个数的最小数
    public int min(int a, int b, int c) {
        if (a < b) {
            if (a < c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b < c) {
                return b;
            } else {
                return c;
            }
        }

    }
}
