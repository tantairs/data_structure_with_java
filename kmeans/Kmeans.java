package com.lianxi.kmeans;

/**
 * Created by tantairs on 2016/11/25.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.Math;

public class Kmeans {

    Collection<Point> totolPoints = new ArrayList<>();
    private int kinds; //一共分为多少个簇
    private Cluster[] clusters;
    Point[] centerPoints;
    Point[] tempPoints; //临时的均值点(最新的)
    int[] initPointId;

    public Kmeans(int kinds){
        this.kinds = kinds;
        clusters = new Cluster[kinds];
        centerPoints = new Point[kinds];
        initPointId = new int[kinds];
        tempPoints = new Point[kinds];
        for(int i = 0; i < kinds; i++){
            clusters[i] = new Cluster();
            centerPoints[i] = new Point();
            tempPoints[i] = new Point();
        }
    }

    public static void main(String[] args){
        String path = "/Users/tantairs/Desktop/Wine_Data/Wine_data_CleanUp_New_test.txt";
        int kinds = 6;
        Kmeans kmeans = new Kmeans(kinds);
        kmeans.doKmeansProcess(path);

    }

    public void doKmeansProcess(String soucePath){
        init(soucePath);
        assignDataForInit();
        do{
            updateData();
            assignDataRunning(tempPoints);
        }
        while (isNeededContinue());

        printResult();


    }

    public void printResult(){
        for(int i = 0; i < kinds; i++){
            List<Point> pointList = clusters[i].getList();
            System.out.print("第" + i + "个簇里的ID为: ");
            for(Point point : pointList){
                System.out.print(point.getId()+",");
            }
            System.out.println();
        }
    }

    /**
     * 分配数据
     *
     * 根据一开始随机的点,将其它的点按照距离的大小归入这N个簇中
     */
    public void assignDataForInit(){

        for(Point point : totolPoints){
            int endKind = -1;
            double maxValue = Double.MAX_VALUE;
            for(int i = 0; i < kinds; i++){
                double tempValue = distanceBetweenTwoPoints(point,getPointById(initPointId[i]));
                if(tempValue <= maxValue){
                    endKind = i;
                    maxValue = tempValue;
                }
            }
            List<Point> lp = clusters[endKind].getList();
            lp.add(point);
        }
    }

    /**
     * 通过获取的均值点重新计算分簇的情况
     * @param initPoints
     */
    public void assignDataRunning(Point[] initPoints){

        for(Point point : totolPoints){
            int endKind = -1;
            double maxValue = Double.MAX_VALUE;
            for(int i = 0; i < kinds; i++){
                double tempValue = distanceBetweenTwoPoints(point,initPoints[i]);
                if(tempValue <= maxValue){
                    endKind = i;
                    maxValue = tempValue;
                }
            }
            clusters[endKind].getList().add(point);
        }
    }

    public Point getPointById(int id){
        Point result = null;
        Iterator<Point> iterator = totolPoints.iterator();
        while (iterator.hasNext()){
            Point point = iterator.next();
            if(point.getId() == id){
                result = point;
                break;
            }
        }
        return result;
    }

    /**
     * 计算两个点之间的欧式距离
     * @param point1
     * @param point2
     * @return
     */
    public double distanceBetweenTwoPoints(Point point1, Point point2){
        double distance = 0;
        double[] data1 = point1.getData();
        double[] data2 = point2.getData();
        int dimension = point1.getDimension();
        for(int i = 0; i < dimension; i++){
            distance = distance + Math.pow(data1[i] - data2[i],2);
        }
        return Math.sqrt(distance);
    }

    /**
     *计算一个list中的均值点
     *
     */
    public double[] distanceForPointsInAList(List<Point> pointList){
        int dimensions = pointList.get(0).getDimension();
        double[] data = new double[dimensions];
        for(Point point : pointList){
            double[] dataTemp = point.getData();
            for(int i = 0; i < dimensions; i++){
                data[i] += dataTemp[i];
            }
        }

        int total = pointList.size();

        for(int i = 0; i < dimensions; i++){
            data[i]/=total;
        }

        return data;
    }

    /**
     * 更新数据,重新计算得到均值点
     */
    public void updateData(){
        for(int i = 0; i < kinds; i++){
            List<Point> pointList = clusters[i].getList();
            tempPoints[i].setId(i);
            tempPoints[i].setData(distanceForPointsInAList(pointList));
            List<Point> list = new ArrayList<>();
            clusters[i].setList(list);
        }

    }

    /**
     *
     * @return true : 没有收敛,需要继续  false: 已经收敛,没有必要继续
     */
    public boolean isNeededContinue(){

        //判断是否已经收敛
        if(caculateCost(centerPoints,tempPoints)){
            return false;
        }else {
            for(int i = 0; i < kinds; i++){
                centerPoints[i] = tempPoints[i];
            }
        }
        return true;

    }

    /**
     * 收敛函数
     * @param points1
     * @param points2
     * @return
     */
    public boolean caculateCost(Point[] points1, Point[] points2){
        double sumDistance = 0;
        for(int i = 0; i < points1.length; i++){
            sumDistance += distanceBetweenTwoPoints(points1[i], points2[i]);
        }

        if(sumDistance < 0.1){
            return true;
        }
        return false;
    }

    /**
     * 初始化数据
     * @param path
     */
    public void init(String path){
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf8"));
            String line = null;
            int columnCount = 9;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine())!= null){
                String[] colums = line.split("\t");
                if(colums.length != columnCount){
                    continue;
                }
                Point point = new Point(columnCount-2); //去掉首部的ID编号和尾部的人工标记
                point.setId(Integer.parseInt(colums[0])); //设置数据的ID编号
                point.setArtificialKind(Integer.parseInt(colums[columnCount-1])); //设置数据的人工编号
                double[] data = new double[columnCount-2];
                for(int i = 1; i < columnCount-1; i++){
                    data[i-1] = Double.parseDouble(colums[i]);
                }
                point.setData(data); //设置真正的数值
                totolPoints.add(point); //将点设置到容器中去
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(totolPoints.size());
        //随机取 N 个点,作为N个簇的初始点
        for(int i = 0; i < kinds; i++){
            initPointId[i] = new Random().nextInt(totolPoints.size()-2) + 2; // 生成 ID 为 2 ~ totolPoints.size()-1 之间的随机数
        }
    }


}
