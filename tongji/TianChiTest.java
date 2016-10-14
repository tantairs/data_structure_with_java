package com.lianxi.tongji;

import com.lianxi.util.DBUtil;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tantairs on 16/5/6.
 */
public class TianChiTest {

    HashMap<String,Integer> hashMap1 = new HashMap<>();
    HashMap<String,Integer> hashMap2 = new HashMap<>();
    HashMap<String,Integer> hashMap3 = new HashMap<>();
    public static void main(String[] args){

        TianChiTest tianChiTest = new TianChiTest();
        tianChiTest.doData();

    }

    public void doData(){
        try {
            String encoding = "utf8";
            File file = new File("/Users/tantairs/Desktop/提交结果和分数/mars_tianchi_artist_plays_predict_1.csv");
            File file1 = new File("/Users/tantairs/Desktop/提交结果和分数/mars_tianchi_artist_plays_predict_4.csv");
            File file2 = new File("/Users/tantairs/Desktop/提交结果和分数/mars_tianchi_artist_plays_predict_5.csv");

            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tempColoum = line.split(",");
                    String key = tempColoum[0].substring(1,tempColoum[0].length()-1).trim()+"#"+tempColoum[2];
                    Integer value = Integer.parseInt(tempColoum[1]);

                    hashMap1.put(key,value);
                }
            }

            if (file1.isFile() && file1.exists()) {
                InputStreamReader reader1 = new InputStreamReader(
                        new FileInputStream(file1), encoding
                );
                BufferedReader bufferedReader1 = new BufferedReader(reader1);
                String line1;
                while ((line1 = bufferedReader1.readLine()) != null) {
                    String[] tempColoum = line1.split(",");
                    String key = tempColoum[0]+"#"+tempColoum[2];
                    Integer value = Integer.parseInt(tempColoum[1]);

                    hashMap2.put(key,value);
                }
            }

            for (Map.Entry<String, Integer> entry : hashMap1.entrySet()) {
                String key = entry.getKey();
                System.out.println(key);
                int value = entry.getValue();
                for (Map.Entry<String, Integer> entry1 : hashMap2.entrySet()) {
                    String  key1 = entry1.getKey();
                    int value2 = entry1.getValue();
                    if(key.equals(key1)){
                        int temp = (value+value2)/2;
                        hashMap3.put(key,temp);

                    }
                }
            }

            FileWriter fileWriter = new FileWriter(file2);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Map.Entry<String, Integer> entry3 : hashMap3.entrySet()) {
                String  key1 = entry3.getKey();
                String[] keys = key1.split("#");
                int value2 = entry3.getValue();
                bufferedWriter.write(keys[0]+","+value2+","+keys[1]);
                bufferedWriter.newLine();
                System.out.println(key1+" : "+value2);
            }
            bufferedWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
