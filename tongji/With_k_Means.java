//package com.lianxi.tongji;
//
//import java.io.*;
//import java.util.*;
//
///**
//* Created by tantairs on 16/3/2.
//*/
//public class With_k_Means {
//
//    int coloum =0;
//    int countRow =1;
//    HashMap<Integer ,List<Integer>> theKinds;
//    int cluster;
//    String[] title = {};
//    boolean value = false;
//    float[][] fullData = new float[1000000][100];
//    float[][] dataTemp = new float[1000000][5];
//    float[][] dataTemp2 = new float[1000000][5];
//
//
//    public static void main(String[] args){
//        With_k_Means with_k_means = new With_k_Means();
//        with_k_means.init(5);
//
//        with_k_means.clusterData();
//        with_k_means.printResult();
//
//    }
//
//
//    public void printResult(){
//        for(int i = 0; i < cluster; i++){
//            Iterator iterator =  theKinds[i].iterator();
//            while (iterator.hasNext()){
//                System.out.print(iterator.next() + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    //初始化聚类的簇的个数
//    public void init(int kinds){
//        cluster = kinds;
////        theKinds= new HashMap<Integer ,List<Integer>>();
//        dataDeal();
//        for(int i = 0; i < kinds; i++){
//            int a = generateRandom();
//            theKinds.put(i,a);
//            for(int j = 1; j < countRow; j++){
//                dataTemp[j][i] = fullData[j][(Integer)theKinds.get(i)];
//            }
//        }
//
//    }
//
//    //生成随机数范围在[1,91]
//    public int generateRandom(){
//        Random r = new Random();
//        int randomCol = r.nextInt(coloum-1)+1;
//        return randomCol;
//    }
//
//    //进行数据的聚类
//    public void clusterData(){
//        double tempSum = 0;
//        double[] min = new double[cluster];
//        copyArray(dataTemp,dataTemp2);
//        for(int i = 1; i < coloum; i++){
//            for(int k = 0; k < cluster; k++){
//                for(int j = 1; j < countRow; j++){
//                    tempSum += Math.pow((fullData[j][i] - dataTemp[j][k]),2);
//                }
//                min[k] = Math.sqrt(tempSum);
//            }
//            // 求最小距离的那个簇的编号
//            theKinds.put(miniData(min),i);
//
//        }
//        average();
//        while (!errorIsNeed(dataTemp,dataTemp2)){
//            clusterData();
//        }
//    }
//
//    public void copyArray(float[][] a1, float[][] a2){
//        for(int i = 1; i < countRow; i++){
//            for(int j = 0; j < cluster; j++){
//                a2[i][j] = a1[i][j];
//            }
//        }
//    }
//    public int miniData(double[] aa){
//        int temp = 0;
//        double value = aa[0];
//        for(int i = 1; i < aa.length; i++){
//
//            if(aa[i] < value){
//                value = aa[i];
//                temp = i;
//            }
//        }
//        return temp;
//    }
//
//    // 求每个簇的中心
//    public void average(){
//        int theKindsSize = 0;
//        int counter = 0;
//        float tempData = 0;
//        for(int i = 0; i < cluster; i++){
//            Iterator iterators = theKinds[i].iterator();
//            theKindsSize = theKinds.get(i)
//            int[] theNumberOfPerCluster = new int[theKindsSize];
//            while (iterators.hasNext()){
//                counter++;
//                theNumberOfPerCluster[counter] = (Integer)iterators.next();
//            }
//            for(int j = 1; j < countRow; j++){
//                for(int z = 0; z < theKindsSize; z++){
//                    tempData+=fullData[j][z];
//                }
//                tempData = tempData/theKindsSize;
//                dataTemp[j][i] = tempData;
//            }
//        }
//    }
//
//    public boolean errorIsNeed(float[][] a1, float[][] a2){
//
//        float[] tempSum = {};
//        float[] temp = {};
//        float aa = 0;
//        for(int i = 1; i < cluster; i++){
//            for(int j = 1; j < countRow; j++){
//                tempSum[i] += a1[j][i];
//                temp[i] += (a2[j][i] - a1[j][i]);
//            }
//            temp[i] = temp[i]/tempSum[i];
//        }
//
//        for(int i = 0; i < cluster; i++){
//            aa += temp[i];
//        }
//
//        if(aa < 0.8 )
//            value = true;
//        return value;
//    }
//
//
//    //处理数据并且返回相应的二维数组
//    public void dataDeal(){
//
//
//        int[] number ={};
//        try {
//            String encoding = "utf8";
//            File file = new File("/Users/tantairs/Downloads/Dueck2015-RawMatrix.txt");
////            File file1 = new File("/Users/tantairs/Downloads/result.IN");
//
//            if(file.isFile()&&file.exists()){
//                InputStreamReader reader = new InputStreamReader(
//                        new FileInputStream(file),encoding
//                );
//                BufferedReader bufferedReader = new BufferedReader(reader);
//                String line;
//                title = bufferedReader.readLine().split("\t");
//                coloum = title.length ;
//                number = new int[coloum];
//                while ((line = bufferedReader.readLine()) != null){
//                    countRow++;
//                    String [] tempColoum = line.split("\t");
//                    for(int i = 1; i < coloum; i++){
//                        try {
//                            number[i] = Integer.parseInt(tempColoum[i]);
//                        }catch (NumberFormatException e){
//                            e.printStackTrace();
//                        }
//                    }
//
//                    for(int i = 1; i < coloum; i++){
//                        fullData[countRow][i] = number[i];
//                    }
//
//                }
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
