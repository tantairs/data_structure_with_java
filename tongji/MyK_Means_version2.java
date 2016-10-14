package com.lianxi.tongji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by tantairs on 16/3/7.
 */
public class MyK_Means_version2 {

    public static void main(String[] args) {
        MyK_Means_version2 myK_means_version2 = new MyK_Means_version2();
        myK_means_version2.dataDeal();
        myK_means_version2.init(4);
        myK_means_version2.calCenter();
        myK_means_version2.recursionKluster();
//        myK_means_version2.printResult();
    }

    int coloum = 0;
    int countRow = 0;
    int rowNumber = 0;
    String[] title = {};
    int cluster = 0;
    private final static Double converge = 0.00000000000000000000001; //当距离小于某个值的时候，就认为聚类已经聚类了，不需要再迭代，这里的值选0.001
    float[][] fullData = {};
    Vector<Simple> totalSimpleSet = new Vector<Simple>();
    List<Vector<Simple>> list = new ArrayList<Vector<Simple>>();


    //初始化和第一次迭代
    //经过测试没有问题
    public void init(int kinds) {
        cluster = kinds;
        for (int i = 0; i < cluster; i++) {
            Vector<Simple> vector = new Vector<Simple>();
            Simple simple = totalSimpleSet.get(i);
            vector.add(simple);
            list.add(vector);
        }

        System.out.println("第1次迭代");

        for (int i = cluster; i < totalSimpleSet.size(); i++) {
            Simple simple = totalSimpleSet.get(i);

            int index = -1;
            double nearDist = Double.MAX_VALUE;

            for (int j = 0; j < cluster; j++) {
                Simple centre = list.get(j).get(0);
                double currentDist = distanceMeasure(simple, centre);
                if (currentDist < nearDist) {
                    nearDist = currentDist;
                    index = j;
                }

            }

//            System.out.println("样本" + i + "被分配到簇的编号为 " + index);

            list.get(index).add(simple);

        }

//        printResult();
    }

    public void printResult() {
        for (int i = 0; i < list.size(); i++) {
            Vector<Simple> vector = list.get(i);
            System.out.println("第" + i + "个簇里面的样本个数为: " + vector.size());
        }
    }

    public Double distanceMeasure(Simple simple1, Simple simple2) {
        double sum = 0;
        double[] temp1, temp2 = {};
        temp1 = simple1.getFeature();
        temp2 = simple2.getFeature();
        for (int i = 0; i < temp1.length; i++) {
            sum += Math.pow(temp1[i] - temp2[i], 2);
        }
        return Math.sqrt(sum);
    }


    //计算新的簇心
    public Double calCenter() {
        double moveDist = 0.0;
        for (int i = 0; i < list.size(); i++) {
            Vector<Simple> vector = list.get(i);
            Simple simple = new Simple();
            double[] simpleTemp, simpleSum = new double[rowNumber];
            int clusterLen = Integer.valueOf(vector.size());
//            System.out.println("正在处理的第几个簇: " + i + ": 长度为:" +clusterLen);
            for (int j = 0; j < clusterLen; j++) {
                Simple simple3 = vector.get(j);
                simpleTemp = simple3.getFeature();//获得一个Simple的特征值
                for (int z = 0; z < rowNumber; z++) {
                    simpleSum[z] += simpleTemp[z];
//                    System.out.println("正在处理的特征值位于第几列(2): " + z);
                }
            }

            for (int k = 0; k < rowNumber; k++) {
                simpleSum[k] = simpleSum[k] / clusterLen;
            }
            simple.setFeature(simpleSum);

            double dist = distanceMeasure(vector.get(0), simple);
            if (dist > moveDist)
                moveDist = dist;
            list.get(i).clear();
            list.get(i).add(simple);
        }

        return moveDist;
    }

    //不断地迭代，直到收敛
    private static Double move = Double.MAX_VALUE;//移动距离

    public void recursionKluster() {
        for (int times = 2; move > converge; times++) {
            System.out.println("第" + times + "次迭代");
            for (int i = 0; i < totalSimpleSet.size(); i++) {
                Simple simple = totalSimpleSet.get(i);
                int index = -1;
                double nearDist = Double.MAX_VALUE;
                for (int k = 0; k < cluster; k++) {
                    Simple centre = list.get(k).get(0);
                    double currentDist = distanceMeasure(simple, centre);
                    if (currentDist < nearDist) {
                        nearDist = currentDist;
                        index = k;
                    }
                }
                list.get(index).add(simple);
                System.out.println("将第"+ simple.getName() +"个点放到簇 "+ index+ "中!");
            }
            System.out.println("---------------------------------------------");
            move = calCenter();
            System.out.println("各个簇心移动中最小的距离为，move=" + move);
        }
    }

    public void dataDeal() {
        int[] number = {};
        try {
            String encoding = "utf8";
            File file = new File("/Users/tantairs/Downloads/datatest.txt");
            File file2 = new File("/Users/tantairs/Downloads/datatest.txt");
//            File file1 = new File("/Users/tantairs/Downloads/Dueck2015-RawMatrix.txt");

            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                InputStreamReader reader2 = new InputStreamReader(
                        new FileInputStream(file2), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                BufferedReader bufferedReader2 = new BufferedReader(reader2);
                String line2, line;
                title = bufferedReader.readLine().split("\t");
                coloum = title.length - 1;
                number = new int[coloum + 1];

                //计算一共的列数
                while ((line = bufferedReader.readLine()) != null) {
                    ++rowNumber;
                    System.out.println(line);
                }

                fullData = new float[rowNumber][coloum + 1];

                bufferedReader2.readLine();
                while ((line2 = bufferedReader2.readLine()) != null) {
                    countRow++;
                    String[] tempColoum = line2.split("\t");
                    for (int i = 1; i <= coloum; i++) {
                        try {
                            number[i] = Integer.parseInt(tempColoum[i]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }


                    for (int i = 1; i <= coloum; i++) {
                        fullData[countRow - 1][i] = number[i];
                    }
                }

                System.out.println("一共样本数目为: " + rowNumber + ": " + countRow);
                for (int i = 1; i <= coloum; i++) {
                    double[] temp = new double[rowNumber];
                    for (int j = 0; j < countRow; j++) {
                        temp[j] = fullData[j][i];
                    }
                    Simple simple = new Simple(i, title[i]);
                    simple.setFeature(temp);
                    totalSimpleSet.add(simple);
                }

                for (int i = 0; i < coloum; i++) {
                    double[] temp = new double[rowNumber];
                    Simple simple = totalSimpleSet.get(i);
                    temp = simple.getFeature();
                    for (int j = 0; j < temp.length; j++) {
                        System.out.print(temp[j] + ", ");
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
