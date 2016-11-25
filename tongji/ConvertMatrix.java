package com.lianxi.tongji;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tantairs on 2016/11/6.
 */
public class ConvertMatrix {

    double[][] result;
    int lengthArray;
    Map<Integer,List<Integer>> clusterId_NO;
    public static void main(String[] args){
        String inputPath = "/Users/tantairs/Downloads/leukdist.txt";
        String inputClusterPath = "/Users/tantairs/Downloads/leukemia.cls";
        String outputPath = "/Users/tantairs/Downloads/leukdist_Result2.txt ";
        ConvertMatrix cm = new ConvertMatrix();
        cm.doConvert(inputPath,inputClusterPath,outputPath);

    }

    /**
     * 根据预处理的矩阵进行转换
     * 由于是对称矩阵，所以
     * 输出模式的方法如下：
     * # 1 1 1 1 1
     * # # 1 1 1 1
     * # # # 1 1 1
     * # # # # # 1
     * # # # # # #
     *
     * @param inputPath
     * @param outputPath
     */
    public void doConvert(String inputPath, String outputPath){

        try {
            File fileForOutput = new File(outputPath);
            FileWriter fileWriter = new FileWriter(fileForOutput);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            preDealData(inputPath);
            for(int i = 0; i < lengthArray; i++){
                for(int j = i+1; j < lengthArray; j++){
                    System.out.println(i+" " +j);
                    bufferedWriter.write((i+1)+" "+(j+1)+" "+result[i][j]);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获得两个输入文件,然后计算每个簇里面两两元素之间的距离的平均值.
     * 最后输出结果.
     * @param inputMatrixPath
     * @param inputClusterResultPath
     * @param outputPath
     */
    public void doConvert(String inputMatrixPath, String inputClusterResultPath, String outputPath){

        try {
            File fileForOutput = new File(outputPath);
            FileWriter fileWriter = new FileWriter(fileForOutput);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("clusterNo " + " avgDistance");
            bufferedWriter.newLine();
            preDealData(inputMatrixPath);
            preDealClusterResultData(inputClusterResultPath);

            for (Map.Entry<Integer, List<Integer>> entry : clusterId_NO.entrySet()) {

                int clusterId = entry.getKey();
                List<Integer> listNo = entry.getValue();

                Integer[] listNoToArray = listNo.toArray(new Integer[listNo.size()]);
                int length = listNoToArray.length;
                double sum = 0;
                for(int i = 0; i < length; i++){
                    for(int j = i+1; j < length; j++){
                        sum += result[i][j];
                    }
                }
                sum /= (length*(length-1)/2);
                bufferedWriter.write(clusterId+"  "+sum);
                bufferedWriter.newLine();

            }
            bufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将输入文件处理为一个二维数组
     * @param path
     */
    public void preDealData(String path){


        try {
            String encoding = "utf8";
            File fileForInput = new File(path);

            if (fileForInput.isFile() && fileForInput.exists()) {

                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(fileForInput), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);

                //先读一行获得矩阵的列数
                String firstLine = bufferedReader.readLine();
                lengthArray = firstLine.split(" ").length;
                int countRows = 0;
                //再循环读出整个行数
                while (bufferedReader.readLine() != null) {
                    countRows++;
                }

                if(lengthArray != countRows+1){
                    System.out.println("输入的待转换的数据矩阵有问题，行数与列数不一致！");
                }
                result = new double[lengthArray][lengthArray];
                InputStreamReader readerBegin = new InputStreamReader(new FileInputStream(fileForInput),encoding);
                BufferedReader bufferedReaderBegin = new BufferedReader(readerBegin);
                String line;
                int count = 0;
                while ((line = bufferedReaderBegin.readLine()) != null){
                    String[] aLine = line.split(" ");
                    int colLength = aLine.length;
                    if(colLength != lengthArray){
                        System.out.println("第 " + count + " 行数据有问题，列数与前面不一致，为 "+colLength+" 列！");
                    }
                    for(int i = 0; i < colLength; i++){
                        result[count][i] = Double.parseDouble(aLine[i]);
                    }
                    count++;
                }


            }else {
                System.out.println("file is not exist!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void preDealClusterResultData(String inputPath){

        try {
            String encoding = "utf8";
            File fileForInput = new File(inputPath);

            if (fileForInput.isFile() && fileForInput.exists()) {

                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(fileForInput), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);

                //数据在第三行,读到第三行数据
                bufferedReader.readLine();
                bufferedReader.readLine();
                String clusterToNo = bufferedReader.readLine();
                String[] clusterNO = clusterToNo.split(" ");

                int number = clusterNO.length;
                clusterId_NO = new HashMap<>();
                if(number != lengthArray){
                    System.out.println("输入的聚类结果格式或个数不对应.原矩阵一共有"+ lengthArray+"个元素,聚类结果里面一共有"+number+"个元素!");
                    return;
                }

                for(int i = 0; i < number; i++){
                    if(clusterId_NO.containsKey(Integer.parseInt(clusterNO[i]))){
                        clusterId_NO.get(Integer.parseInt(clusterNO[i])).add(i);
                    }else {
                        List<Integer> tempNo = new ArrayList<>();
                        tempNo.add(i);
                        clusterId_NO.put(Integer.parseInt(clusterNO[i]),tempNo);
                    }

                }

            }else {
                System.out.println("file is not exist!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
