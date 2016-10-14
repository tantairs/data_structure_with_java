package com.lianxi.tongji;

import java.io.*;
import java.util.*;

/**
 * Created by tantairs on 16/4/18.
 */
public class WineStatis {
    final int MAXROW = 100000;
    final int COLOUM = 3;
    final int COLOUM2 = 6;
    private int[][] tempdata;
    TreeSet<Integer> titleCol;
    TreeSet<Integer> titleRow;
    private int count;
    Map<Integer, ArrayList<Integer>> mapResultTemp = new HashMap<>();

    public static void main(String[] args) {

        WineStatis wineStatis = new WineStatis();
//        wineStatis.doStatistic("/Users/tantairs/Desktop/Wine_Data/middleResult.csv", "/Users/tantairs/Desktop/result.csv");
//        wineStatis.doStatisticBySecondWay("/Users/tantairs/Desktop/Wine_Data/Wine_data_CleanUp_New.txt","/Users/tantairs/Downloads/element_CP12_20160326232702_Euclidean.txt","/Users/tantairs/Desktop/result.csv");
        double[] average = wineStatis.doStatisticWithKmeansVar("/Users/tantairs/Desktop/Wine_Data/middleResult2.csv", "/Users/tantairs/Desktop/resultdata.csv");
        wineStatis.doKmeansStatis("/Users/tantairs/Desktop/Wine_Data/middleResult2.csv", average, "/Users/tantairs/Desktop/resultdata.csv");
    }

    /**
     * @param path 原始文件数据路径,该文件是一个txt格式的文件
     * @return 一个ID 与 quality 的映射集s
     */
    public Map readInitData(String path) {
        //存放ID(name) 与 对应quality的映射
        Map<Integer, Integer> maps = new HashMap<>();
        try {
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                bufferedReader.readLine();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split("\t");
                    maps.put(Integer.parseInt(tempColoum[0]), Integer.parseInt(tempColoum[8]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    /**
     * 对第二种数据格式进行统计.
     * 方法无需设置簇的个数,自动对簇的个数进行识别,并输出结果
     *
     * @param sourcePath 原数据路径,处理该文件,为了获得数据ID 与 quality 的映射关系
     * @param inputPath  待分析的三列数据文件路径
     * @param outputPath 输出的结果
     */
    public void doStatisticBySecondWay(String sourcePath, String inputPath, String outputPath) {
        titleCol = new TreeSet<>();
        titleRow = new TreeSet<>();
        tempdata = new int[MAXROW][COLOUM];
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Float> arrayListMean = new ArrayList<>();
        ArrayList<Float> arrayListVar = new ArrayList<>();
        Map<Integer, Integer> mapSource;
        int length = 0;

        //获取原数据中ID 与 quality 的映射关系
        mapSource = readInitData(sourcePath);

        try {
            File file = new File(inputPath);
            File file1 = new File(outputPath);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = null;
                length = bufferedReader.readLine().split("\t").length;
                bufferedReader.readLine();
                bufferedReader.readLine();
                bufferedReader.readLine();

                //把数据读入到一个二维数组 tempdata[][]中 这里为 N 行 两列,第一列是簇ID,第二列是quality
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split("\t");
                    titleCol.add(Integer.parseInt(tempColoum[0]));
                    titleRow.add(mapSource.get(Integer.parseInt(tempColoum[2])));
                    for (int i = 0; i < length; i += 2) {
                        if (i > 1) {
                            tempdata[count][i] = mapSource.get(Integer.parseInt(tempColoum[i]));
                        } else {
                            tempdata[count][i] = Integer.parseInt(tempColoum[i]);
                        }
                    }
                    count++;
                    bufferedReader.readLine();
                }
                Iterator<Integer> iterator = titleCol.iterator();
                while (iterator.hasNext()) {
                    int number = iterator.next();
                    ArrayList list = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        if (tempdata[i][0] == number)
                            list.add(tempdata[i][2]);
                    }
                    mapResultTemp.put(number, list);
                }

                FileWriter fileWriter = new FileWriter(file1);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("quality/cluster" + ",");
                Iterator<Integer> iterator1 = titleCol.iterator();
                while (iterator1.hasNext()) {
                    bufferedWriter.write(iterator1.next() + ",");
                }
                bufferedWriter.newLine();
                Iterator<Integer> iterator2 = titleCol.iterator();
                while (iterator2.hasNext()) {
                    int num = iterator2.next();
                    int sum_frequency = 0;
                    int sum_mean = 0;
                    int sum_var = 0;
                    float ave = 0.0f;
                    float var = 0.0f;
                    ArrayList list = (ArrayList) mapResultTemp.get(num);
                    Map<Integer, Integer> mapSub = countFrequencyByKey(list);
                    for (Map.Entry<Integer, Integer> entry : mapSub.entrySet()) {
                        int key = entry.getKey();
                        int value = entry.getValue();
                        //分别计算 频数, 期望, 方差
                        sum_frequency += value;
                        sum_mean += key * value;
                        sum_var += key * key * value;
                        arrayList.add(value);
                    }
                    ave = (float) sum_mean / sum_frequency;
                    var = (float) sum_var / sum_frequency - ave * ave;
                    arrayListMean.add(ave);
                    arrayListVar.add(var);
                }
                //格式化输出结果
                int director = 0;
                int mod = 0;
                int splitFormat = titleRow.size();
                Iterator<Integer> titleRowIteraot = titleRow.iterator();
                while (titleRowIteraot.hasNext()) {
                    Iterator<Integer> iterator3 = arrayList.iterator();
                    String result = titleRowIteraot.next() + ",";
                    while (iterator3.hasNext()) {
                        int num = iterator3.next();
                        if (director++ % splitFormat == mod) {
                            result += (num + ",");
                        }
                    }
                    mod++;
                    director = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

                String exp = "exp" + ",";
                Iterator<Float> iteratorExp = arrayListMean.iterator();
                while (iteratorExp.hasNext()) {
                    exp += iteratorExp.next() + ",";
                }
                bufferedWriter.write(exp);
                bufferedWriter.newLine();

                String var = "var" + ",";
                Iterator<Float> iteratorVar = arrayListVar.iterator();
                while (iteratorVar.hasNext()) {
                    var += iteratorVar.next() + ",";
                }
                bufferedWriter.write(var);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对R中kmeans后的数据进行统计
     *
     * @param inputPath
     * @param outputPath
     */
    public void doStatisticAfterKmeans(String inputPath, String outputPath) {
        titleCol = new TreeSet<>();
        titleRow = new TreeSet<>();
        //这里存放簇ID 和 quality
        tempdata = new int[MAXROW][COLOUM];
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Float> arrayListMean = new ArrayList<>();
        ArrayList<Float> arrayListVar = new ArrayList<>();

        int length = 0;
        try {
            File file = new File(inputPath);
            File file1 = new File(outputPath);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = null;
                length = bufferedReader.readLine().split(",").length;

                //把数据读入到一个二维数组 tempdata[][]中,这里的二维数组存放的是序号,quality 和簇ID
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split(",");
                    titleCol.add(Integer.parseInt(tempColoum[2]));
                    titleRow.add(Integer.parseInt(tempColoum[1]));
                    //存放数组
                    for (int i = 0; i < length - 1; i++) {
                        tempdata[count][i] = Integer.parseInt(tempColoum[i + 1]);
                    }
                    //输入
                    count++;
                }
                Iterator<Integer> iterator = titleCol.iterator();
                while (iterator.hasNext()) {
                    int number = iterator.next();
                    ArrayList list = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        if (tempdata[i][1] == number)
                            list.add(tempdata[i][0]);
                    }
                    mapResultTemp.put(number, list);
                }

                FileWriter fileWriter = new FileWriter(file1);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("quality/cluster" + ",");
                Iterator<Integer> iterator1 = titleCol.iterator();
                while (iterator1.hasNext()) {
                    bufferedWriter.write(iterator1.next() + ",");
                }
                bufferedWriter.newLine();
                Iterator<Integer> iterator2 = titleCol.iterator();
                while (iterator2.hasNext()) {
                    int num = iterator2.next();
                    int sum_frequency = 0;
                    int sum_mean = 0;
                    int sum_var = 0;
                    float ave = 0.0f;
                    float var = 0.0f;
                    ArrayList list = (ArrayList) mapResultTemp.get(num);
                    Map<Integer, Integer> mapSub = countFrequencyByKey(list);
                    for (Map.Entry<Integer, Integer> entry : mapSub.entrySet()) {
                        int key = entry.getKey();
                        int value = entry.getValue();
                        //分别计算 频数, 期望, 方差
                        sum_frequency += value;
                        sum_mean += key * value;
                        sum_var += key * key * value;
                        arrayList.add(value);
                    }
                    ave = (float) sum_mean / sum_frequency;
                    var = (float) sum_var / sum_frequency - ave * ave;
                    arrayListMean.add(ave);
                    arrayListVar.add(var);
                }
                //格式化输出结果
                int director = 0;
                int mod = 0;
                int splitFormat = titleRow.size();
                Iterator<Integer> titleRowIteraot = titleRow.iterator();
                while (titleRowIteraot.hasNext()) {
                    Iterator<Integer> iterator3 = arrayList.iterator();
                    String result = titleRowIteraot.next() + ",";
                    while (iterator3.hasNext()) {
                        int num = iterator3.next();
                        System.out.print(num + ",");
                        if (director++ % splitFormat == mod) {
                            result += (num + ",");
                        }
                    }
                    mod++;
                    director = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

                String exp = "exp" + ",";
                Iterator<Float> iteratorExp = arrayListMean.iterator();
                while (iteratorExp.hasNext()) {
                    exp += iteratorExp.next() + ",";
                }
                bufferedWriter.write(exp);
                bufferedWriter.newLine();

                String var = "var" + ",";
                Iterator<Float> iteratorVar = arrayListVar.iterator();
                while (iteratorVar.hasNext()) {
                    var += iteratorVar.next() + ",";
                }
                bufferedWriter.write(var);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对kmeans 里面的每个簇各个点距离求和,并输出.为了下面的方差计算,返回平均距离
     *
     * @param inputPath
     * @param output
     * @return
     */
    public double[] doStatisticWithKmeansVar(String inputPath, String output) {

        //判断是否是第一个的标记
        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;
        int number6 = 0;

        //统计一个簇内两个点之间两两比较的次数
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;

        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        double sum4 = 0.0;
        double sum5 = 0.0;
        double sum6 = 0.0;

        ArrayList<double[]> arrayList1 = new ArrayList<>();
        ArrayList<double[]> arrayList2 = new ArrayList<>();
        ArrayList<double[]> arrayList3 = new ArrayList<>();
        ArrayList<double[]> arrayList4 = new ArrayList<>();
        ArrayList<double[]> arrayList5 = new ArrayList<>();
        ArrayList<double[]> arrayList6 = new ArrayList<>();
        double[] ave = new double[COLOUM2];
        try {
            File file = new File(inputPath);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = null;
                bufferedReader.readLine();

                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split(",");
                    int cluster = Integer.parseInt(tempColoum[8]);
                    switch (cluster) {
                        case 1:
                            number1++;
                            if (number1 > 1) {
                                double temp1 = 0.0;
                                Iterator<double[]> iterator = arrayList1.iterator();
                                while (iterator.hasNext()) {
                                    count1++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp1 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp1 = Math.sqrt(temp1);
                                sum1 += temp1;

                                double[] data1 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data1[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList1.add(data1);

                            } else {
                                double[] data1 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data1[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList1.add(data1);
                                continue;
                            }
                            break;

                        case 2:
                            number2++;
                            if (number2 > 1) {
                                double temp2 = 0.0;
                                Iterator<double[]> iterator = arrayList2.iterator();
                                while (iterator.hasNext()) {
                                    count2++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp2 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp2 = Math.sqrt(temp2);
                                sum2 += temp2;

                                double[] data2 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data2[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList2.add(data2);

                            } else {
                                double[] data2 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data2[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList2.add(data2);
                                continue;
                            }
                            break;
                        case 3:
                            number3++;
                            if (number3 > 1) {
                                double temp3 = 0.0;
                                Iterator<double[]> iterator = arrayList3.iterator();
                                while (iterator.hasNext()) {
                                    count3++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp3 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp3 = Math.sqrt(temp3);
                                sum3 += temp3;

                                double[] data3 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data3[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList3.add(data3);

                            } else {
                                double[] data3 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data3[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList3.add(data3);
                                continue;
                            }
                            break;
                        case 4:
                            number4++;
                            if (number4 > 1) {
                                double temp4 = 0.0;
                                Iterator<double[]> iterator = arrayList4.iterator();
                                while (iterator.hasNext()) {
                                    count4++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp4 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp4 = Math.sqrt(temp4);
                                sum4 += temp4;

                                double[] data4 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data4[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList4.add(data4);


                            } else {
                                double[] data4 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data4[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList4.add(data4);
                                continue;
                            }
                            break;
                        case 5:
                            number5++;
                            if (number5 > 1) {
                                double temp5 = 0.0;
                                Iterator<double[]> iterator = arrayList5.iterator();
                                while (iterator.hasNext()) {
                                    count5++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp5 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp5 = Math.sqrt(temp5);
                                sum5 += temp5;

                                double[] data5 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data5[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList5.add(data5);

                            } else {
                                double[] data5 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data5[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList5.add(data5);
                                continue;
                            }
                            break;
                        case 6:
                            number6++;
                            if (number6 > 1) {
                                double temp6 = 0.0;
                                Iterator<double[]> iterator = arrayList6.iterator();
                                while (iterator.hasNext()) {
                                    count6++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp6 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp6 = Math.sqrt(temp6);
                                sum6 += temp6;

                                double[] data6 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data6[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList6.add(data6);

                            } else {
                                double[] data6 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data6[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList6.add(data6);
                                continue;
                            }
                            break;
                    }


                }
                ave[0] = sum1 / count1;
                ave[1] = sum2 / count2;
                ave[2] = sum3 / count3;
                ave[3] = sum4 / count4;
                ave[4] = sum5 / count5;
                ave[5] = sum6 / count6;
            }
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("sum of distance every cluster");
            bufferedWriter.newLine();
            bufferedWriter.write("1," + "2," + "3," + "4," + "5," + "6");
            bufferedWriter.newLine();
            bufferedWriter.write(sum1 + "," + sum2 + "," + sum3 + "," + sum4 + "," + sum5 + "," + sum6);
            bufferedWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return ave;
    }

    /**
     * 通过传入的平均距离计算方差.逻辑与上面的相同,只是接受了一个均值来计算方差
     *
     * @param path    这里的输入与上面的也相同
     * @param average
     * @param output2 这里的输出与上面的求和距离相同,结果append到上面结果文件中
     */
    public void doKmeansStatis(String path, double[] average, String output2) {

        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;
        int number6 = 0;

        //统计一个簇内两个点之间两两比较的次数
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;

        double sum1 = 0.0;
        double sum2 = 0.0;
        double sum3 = 0.0;
        double sum4 = 0.0;
        double sum5 = 0.0;
        double sum6 = 0.0;
        ArrayList<double[]> arrayList1 = new ArrayList<>();
        ArrayList<double[]> arrayList2 = new ArrayList<>();
        ArrayList<double[]> arrayList3 = new ArrayList<>();
        ArrayList<double[]> arrayList4 = new ArrayList<>();
        ArrayList<double[]> arrayList5 = new ArrayList<>();
        ArrayList<double[]> arrayList6 = new ArrayList<>();

        try {
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = null;
                bufferedReader.readLine();

                //把数据读入到一个二维数组 tempdata[][]中
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split(",");
                    int cluster = Integer.parseInt(tempColoum[8]);
                    switch (cluster) {
                        case 1:
                            number1++;
                            if (number1 > 1) {
                                double temp1 = 0.0;
                                Iterator<double[]> iterator = arrayList1.iterator();
                                while (iterator.hasNext()) {
                                    count1++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp1 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp1 = Math.sqrt(temp1);
                                temp1 = Math.pow(temp1 - average[0], 2);
                                sum1 += temp1;

                                double[] data1 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data1[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList1.add(data1);

                            } else {
                                double[] data1 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data1[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList1.add(data1);
                                continue;
                            }
                            break;

                        case 2:
                            number2++;
                            if (number2 > 1) {
                                double temp2 = 0.0;
                                Iterator<double[]> iterator = arrayList2.iterator();
                                while (iterator.hasNext()) {
                                    count2++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp2 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp2 = Math.sqrt(temp2);
                                temp2 = Math.pow(temp2 - average[1], 2);
                                sum2 += temp2;

                                double[] data2 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data2[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList2.add(data2);

                            } else {
                                double[] data2 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data2[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList2.add(data2);
                                continue;
                            }
                            break;
                        case 3:
                            number3++;
                            if (number3 > 1) {
                                double temp3 = 0.0;
                                Iterator<double[]> iterator = arrayList3.iterator();
                                while (iterator.hasNext()) {
                                    count3++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp3 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp3 = Math.sqrt(temp3);
                                temp3 = Math.pow(temp3 - average[2], 2);
                                sum3 += temp3;

                                double[] data3 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data3[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList3.add(data3);

                            } else {
                                double[] data3 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data3[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList3.add(data3);
                                continue;
                            }
                            break;
                        case 4:
                            number4++;
                            if (number4 > 1) {
                                double temp4 = 0.0;
                                Iterator<double[]> iterator = arrayList4.iterator();
                                while (iterator.hasNext()) {
                                    count4++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp4 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp4 = Math.sqrt(temp4);
                                temp4 = Math.pow(temp4 - average[3], 2);
                                sum4 += temp4;

                                double[] data4 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data4[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList4.add(data4);
                            } else {
                                double[] data4 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data4[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList4.add(data4);
                                continue;
                            }
                            break;
                        case 5:
                            number5++;
                            if (number5 > 1) {
                                double temp5 = 0.0;
                                Iterator<double[]> iterator = arrayList5.iterator();
                                while (iterator.hasNext()) {
                                    count5++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp5 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp5 = Math.sqrt(temp5);
                                temp5 = Math.pow(temp5 - average[4], 2);
                                sum5 += temp5;

                                double[] data5 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data5[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList5.add(data5);

                            } else {
                                double[] data5 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data5[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList5.add(data5);
                                continue;
                            }
                            break;
                        case 6:
                            number6++;
                            if (number6 > 1) {
                                double temp6 = 0.0;
                                Iterator<double[]> iterator = arrayList6.iterator();
                                while (iterator.hasNext()) {
                                    count6++;
                                    double[] object = iterator.next();
                                    for (int i = 0; i < 7; i++) {
                                        temp6 += Math.pow((Double.parseDouble(tempColoum[i + 1]) - object[i]), 2);
                                    }
                                }
                                temp6 = Math.sqrt(temp6);
                                temp6 = Math.pow(temp6 - average[5], 2);
                                sum6 += temp6;

                                double[] data6 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data6[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList6.add(data6);

                            } else {
                                double[] data6 = new double[tempColoum.length - 2];
                                for (int i = 0; i < 7; i++) {
                                    data6[i] = Double.parseDouble(tempColoum[i + 1]);
                                }
                                arrayList6.add(data6);
                                continue;
                            }
                            break;
                    }


                }
                FileWriter fileWriter = new FileWriter(output2, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                bufferedWriter.write("var of distance every cluster");
                bufferedWriter.newLine();
                bufferedWriter.write("1" + "," + "2" + "," + "3" + "," + "4" + "," + "5" + "," + "6");
                bufferedWriter.newLine();
                bufferedWriter.write(sum1 / count1 + "," + sum2 / count2 + "," + sum3 / count3 + "," + sum4 / count4 + "," + sum5 / count5 + "," + sum6 / count6);
                bufferedWriter.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //计算每个list 里面各个数字的频数,并将结果保存到一个map中
    public Map countFrequencyByKey(ArrayList list) {
        Map<Integer, Integer> maps = new TreeMap<>();
        Iterator<Integer> iterator = titleRow.iterator();
        while (iterator.hasNext()) {
            int row = iterator.next();
            int counttmp = 0;
            Iterator<Integer> iterator1 = list.iterator();
            while (iterator1.hasNext()) {
                if (iterator1.next() == row)
                    counttmp++;
            }
            maps.put(row, counttmp);
        }
        return maps;
    }
}
