package com.lianxi.tongji;

import java.io.*;

/**
 * Created by tantairs on 16/2/27.
 */
public class GenuisTest {

    public static void main(String[] args) {
//       convertFile();
//        dataDealWithPearson();
//        dataDealWithMinkowski();
        dataDealWithPearson_RowBased();
    }

    public static void dataDealWithMinkowski() {
        String[] title = {};
        float[] number = {};
        int countRow = 1;
        double sum = 0;
        int coloum = 0;
        float[][] fullData = new float[1000000][100];
        float addSum = 0;
        String result = null;

        try {
            String encoding = "utf8";
            File file = new File("/Users/tantairs/Downloads/Dueck2015-log1p-deseqnorm-scaled.txt");
            File file1 = new File("/Users/tantairs/Downloads/Dueck2015-log1p-deseqnorm-scaled_result_Minkowski.IN");

            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                title = bufferedReader.readLine().split("\t");
                coloum = title.length;
                number = new float[coloum];
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split("\t");
                    for (int i = 1; i < coloum; i++) {
                        try {
                            number[i] = Float.parseFloat(tempColoum[i]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 1; i < coloum; i++) {

                        sum += number[i];
                    }

                    if (sum > coloum / 2) {
                        countRow++;
                        for (int i = 1; i < coloum; i++) {
                            fullData[countRow][i] = number[i];
                        }

                    }
                }
            }

            System.out.println("一共的基因个数(有效): " + (countRow - 1));


            FileWriter fileWriter = new FileWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write(coloum - 1 + "   " + (coloum - 1) + "   " + "999");
            bufferedWriter.newLine();
            for (int i = 1; i < coloum - 1; i++) {
                for (int j = i + 1; j < coloum; j++) {
                    for (int z = 1; z < countRow; z++) {
                        addSum += Math.pow(Math.log((fullData[z][j]) + 2) - Math.log(fullData[z][i] + 2), 2);
                    }
                    result = title[i] + "   " + title[j] + "   " + Math.sqrt(addSum);
                    addSum = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void dataDealWithPearson() {
        int test = 1;
        String[] title = {};
        float[] number = {};
        int countRow = 1;
        double sum = 0;
        int coloum = 0;
        float[][] fullData = new float[1000000][100];
        double a = 0, b = 0, c = 0;
        double[] average = new double[100];
        String result = null;

        try {
            String encoding = "utf8";
            File file = new File("/Users/tantairs/Downloads/Dueck2015-RawMatrix_result.txt");
            File file1 = new File("/Users/tantairs/Downloads/Dueck2015-RawMatrix_result_result_pearson.IN");

            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                title = bufferedReader.readLine().split("\t");
                coloum = title.length;
                number = new float[coloum];


                while ((line = bufferedReader.readLine()) != null) {

                    String[] tempColoum = line.split("\t");
                    for (int i = 1; i < coloum; i++) {
                        try {
                            number[i] = Float.parseFloat(tempColoum[i]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 1; i < coloum; i++) {

                        sum += number[i];
                    }

                    if (sum > (coloum - 1) / 2) {
                        countRow++;
                        for (int i = 1; i < coloum; i++) {
                            fullData[countRow][i] = number[i];
                        }

                    }
                }
            }

            System.out.println("一共的基因个数(有效): " + (countRow - 1));


            FileWriter fileWriter = new FileWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write((coloum - 1) + "   " + (coloum - 1) + "   " + "999");
            bufferedWriter.newLine();

            for (int i = 1; i < coloum; i++) {
                double sumtemp = 0.0;
                for (int j = 1; j < countRow; j++) {
                    sumtemp += fullData[j][i];
                }
                average[i] = sumtemp / (countRow - 1);
            }

            for (int i = 1; i < coloum - 1; i++) {
                for (int j = i + 1; j < coloum; j++) {
                    for (int z = 1; z < countRow; z++) {
                        a += ((fullData[z][i]) - average[i]) * (fullData[z][j] - average[j]);
                        b += Math.pow((fullData[z][i]) - average[i], 2);
                        c += Math.pow((fullData[z][j]) - average[j], 2);
                    }
                    result = title[i] + "   " + title[j] + "   " + a / (Math.sqrt(b) * Math.sqrt(c));
                    a = b = c = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void dataDealWithPearson_RowBased() {

        String[] title = new String[92];
        float[] number = {};
        int countRow = 1;
        int coloum = 0;
        float[][] fullData = new float[100][10];
        double a = 0, b = 0, c = 0;
        double[] average = new double[100];
        String result = null;

        try {
            String encoding = "utf8";
            File file = new File("/Users/tantairs/Desktop/Bio/Dueck2015-RawMatrix.log.scaled.PCA.3D.txt");
            File file1 = new File("/Users/tantairs/Desktop/Bio/Dueck2015-RawMatrix.log.scaled.PCA.3.IN");

            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                coloum = bufferedReader.readLine().split("\t").length;
                number = new float[coloum];


                while ((line = bufferedReader.readLine()) != null) {

                    String[] tempColoum = line.split("\t");

                    title[countRow] = tempColoum[0];
                    for (int i = 1; i < coloum; i++) {
                        try {
                            number[i] = Float.parseFloat(tempColoum[i]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }


                    for (int i = 1; i < coloum; i++) {
                        fullData[countRow][i] = number[i];
                    }

                    countRow++;

                }
            }


            FileWriter fileWriter = new FileWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write((countRow-1) + "   " + (countRow-1) + "   " + "999");
            bufferedWriter.newLine();

            //求每一个细胞的基因平均值
            for (int i = 1; i < countRow; i++) {
                double sumtemp = 0.0;
                for (int j = 1; j < coloum; j++) {
                    sumtemp += fullData[i][j];
                }
                average[i] = sumtemp / (coloum-1);
            }

            for (int i = 1; i < countRow-1; i++) {
                for (int j = i + 1; j < countRow; j++) {
                    for (int z = 1; z < coloum; z++) {
                        a += ((fullData[i][z]) - average[i]) * (fullData[j][z] - average[j]);
                        b += Math.pow((fullData[i][z]) - average[i], 2);
                        c += Math.pow((fullData[j][z]) - average[j], 2);
                    }
                    result = title[i] + "   " + title[j] + "   " +(1- a / (Math.sqrt(b) * Math.sqrt(c)));
                    a = b = c = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void convertFile() {
        String encoding = "utf8";
        File file = new File("/Users/tantairs/Downloads/Dueck2015-RawMatrix.txt");
        File file1 = new File("/Users/tantairs/Downloads/convertedFile.txt");
        try {
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                FileWriter fileWriter = new FileWriter(file1);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(line.replaceAll("\t", ", "));
                    bufferedWriter.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
