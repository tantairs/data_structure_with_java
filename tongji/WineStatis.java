package com.lianxi.tongji;

import java.io.*;
import java.util.*;

/**
 * Created by tantairs on 16/4/18.
 */
public class WineStatis {
    final int MAXROW = 100000;
    final int COLOUM = 2;
    int[][] tempdata;
    TreeSet<Integer> titleCol;
    TreeSet<Integer> titleRow;
    int count;
    Map<Integer, ArrayList<Integer>> mapResultTemp = new HashMap<>();

    public static void main(String[] args) {

        WineStatis wineStatis = new WineStatis();
        wineStatis.doStatistic("/Users/tantairs/Desktop/Wine_Data/middleResult.csv", "/Users/tantairs/Desktop/result.csv");
    }

    //对数据进行分组,并且统计各个组里面的不同数字的频数,均值以及方差,并按照相应的格式输出.
    public void doStatistic(String path, String path2) {
        titleCol = new TreeSet<>();
        titleRow = new TreeSet<>();
        tempdata = new int[MAXROW][COLOUM];
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Float> arrayListMean = new ArrayList<>();
        ArrayList<Float> arrayListVar = new ArrayList<>();

        int length = 0;
        try {
            File file = new File(path);
            File file1 = new File(path2);
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), "utf8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = null;
                length = bufferedReader.readLine().split(",").length;

                //把数据读入到一个二维数组 tempdata[][]中
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split(",");
                    titleCol.add(Integer.parseInt(tempColoum[2]));
                    titleRow.add(Integer.parseInt(tempColoum[1]));

                    for (int i = 0; i < length - 1; i++) {
                        tempdata[count][i] = Integer.parseInt(tempColoum[i + 1]);
                    }
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
                Iterator<Integer> titleRowIteraot = titleRow.iterator();
                while (titleRowIteraot.hasNext()) {
                    Iterator<Integer> iterator3 = arrayList.iterator();
                    String result = titleRowIteraot.next() + ",";
                    while (iterator3.hasNext()) {
                        int num = iterator3.next();
                        System.out.print(num + ",");
                        if (director++ % 6 == mod) {
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
