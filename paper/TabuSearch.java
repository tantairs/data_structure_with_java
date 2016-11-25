package com.lianxi.paper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.Math;

/**
 * Created by tantairs on 9/29/16.
 */
public class TabuSearch {

    Collection<Point> totolPoints = new ArrayList<>();
    Map<Integer,Integer> tabuList = new HashMap<>();

    final int tabuSize = 10; //禁忌长度
    boolean isNeededContinueFlag = true;
    final int iteratorTimes = 3000;

    private int kinds; //一共分为多少个簇
    private Cluster[] clusters;
    private int[] centerPoints;
    private int[] centerPointsFinal; //保存最优的中心点
    double priceValue = Double.MAX_VALUE;

    public TabuSearch(int kinds){
        this.kinds = kinds;
        clusters = new Cluster[kinds];
        centerPoints = new int[kinds];
        centerPointsFinal = new int[kinds];
        for(int i = 0; i < kinds; i++){
            clusters[i] = new Cluster();
        }
    }


    public static void main(String[] args){
        String path = "/Users/tantairs/Desktop/Wine_Data/Wine_data_CleanUp_New_test.txt";
        int kinds = 6;
        TabuSearch kmeans = new TabuSearch(kinds);
        kmeans.doKmeansProcess(path);

    }

    public void doKmeansProcess(String soucePath){

        int count = 0;

        //读入数据
        dataPreprocessing(soucePath);

        //初始化中心点
        initCenterPoint();

        //数据分配
        reallocatePoints(centerPoints);

        //进入迭代
        while (count < iteratorTimes && isNeededContinueFlag) { // 判断是否需要继续进行

            //更新中心点
            updateCenterPoint();

            //重新进行数据分配
            reallocatePoints(centerPoints);
            count++;
        }

        printResult();


    }

    /**
     * 打印结果
     */
    public void printResult(){
        //按照最终的中心点分配
        reallocatePoints(centerPointsFinal);

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
     * 刷新禁忌表
     */
    public void reFreshTuboList(){
         for(Map.Entry<Integer,Integer> entry : tabuList.entrySet()){
             int value = entry.getValue();
             if(value == 0){
                 tabuList.remove(entry.getKey());
             }else {
                 tabuList.put(entry.getKey(),--value);
             }

         }
    }

    /**
     * 通过中心点重新计算分簇的情况
     * @param centerPoints
     */
    public void reallocatePoints(int[] centerPoints){

        for(Point point : totolPoints){
            int endKind = -1;
            double maxValue = Double.MAX_VALUE;
            for(int i = 0; i < kinds; i++){
                //为每个簇设置中心点
                clusters[i].setCenterId(centerPoints[i]);

                //centerPoints里面存的值即为中心点
                double distanceOfTwoPoints = distanceBetweenTwoPoints(point,getPointById(centerPoints[i]));
                if(distanceOfTwoPoints <= maxValue){
                    endKind = i;
                    maxValue = distanceOfTwoPoints;
                }
            }
            clusters[endKind].getList().add(point);
        }
    }

    /**
     * 通过ID值返回一个Point对象
     * @param id
     * @return
     */
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
     * 更新数据,重新计算得到中心点
     * 这里通过邻域搜索来获得最新的中心点
     */
    public void updateCenterPoint(){

        double totalPriceValue = 0;

        for(int i = 0; i < kinds; i++){

            //为每个簇生成一个候选集
            Map<Point,Double> candidateList = new HashMap<>();

            //计算该簇中心点的邻域半径 (clusters[i])
            double r = calculateNeighbourRadius(clusters[i]);

            //计算邻域内哪个点的代价值最小
            //代价值的定义为中心点到所有该簇其他点的距离和
            List<Point> pointList = clusters[i].getList();
            for(Point p : pointList){

                //如果p在邻域内
                if(distanceBetweenTwoPoints(p,getPointById(clusters[i].getCenterId())) < r){
                    double priceTemp = calculatePriceEachCluster(p, clusters[i]);

                    //放入候选集
                    candidateList.put(p,priceTemp);
                }

            }
            //候选集中的数值对按照value从小到大进行排序
            List<Map.Entry<Point,Double>> arrayList = new ArrayList(candidateList.entrySet());
            Collections.sort(arrayList,new MyComparator());

            int flag = -1; //设置一个标记,用来判断是否候选集里面的所有元素都在禁忌表中
            for(Map.Entry<Point,Double> mapper: arrayList){
                if(!tabuList.containsKey(mapper.getKey())){

                    //选取一个不在禁忌表中且在该候选集中代价最小的点设为中心点
                    int centerId = mapper.getKey().getId();
                    centerPoints[i] = centerId;

                    //加入到禁忌表
                    tabuList.put(centerId,tabuSize);

                    totalPriceValue += mapper.getValue();
                    flag = 1;
                    break;
                }
            }

            if(flag == -1){
                //说明候选集中所有的点都在禁忌表中
                isNeededContinueFlag = false;
            }

            //!!!IMPORTANT
            //很重要的一步,根据cluster[] 更新完中心点后,需要清空以便下次重新分配数据
            List<Point> list = new ArrayList<>();
            clusters[i].setList(list);

        }

        if(totalPriceValue < priceValue){
            priceValue = totalPriceValue;
            for(int i = 0; i < kinds; i++){
                centerPointsFinal[i] = centerPoints[i];
            }
        }

        reFreshTuboList();

    }

    /**
     * 计算一个簇中以 point 为中心的邻域半径
     * @param cluster
     * @return
     */
    public double calculateNeighbourRadius(Cluster cluster){
        //首先获取该簇的中心点
        int centerNow = cluster.getCenterId();
        int number = cluster.getPointNumbers();
        List<Point> points = cluster.getList();
        double sum = 0;
        for(Point p : points){
            sum += distanceBetweenTwoPoints(p,getPointById(centerNow));
        }

        return sum/(number-1);
    }

    /**
     * 根据中心点和该中心点所在的簇计算簇内的代价函数
     * @param point
     * @param cluster
     * @return
     */
    public double calculatePriceEachCluster(Point point, Cluster cluster){
        List<Point> points = cluster.getList();
        double sum = 0;
        for(Point p : points){
            sum += distanceBetweenTwoPoints(point,p);
        }

        return sum;
    }

//    /**
//     * 如果连续N次代价函数的减少值都在 ε 之内则表示已经收敛
//     * @return true : 没有收敛,需要继续; false: 已经收敛,没有必要继续
//     */
//    public boolean isNeededContinue(){
//
//        //判断是否已经收敛
//        if(caculateCost(centerPoints,tempPoints)){
//            return false;
//        }else {
//            for(int i = 0; i < kinds; i++){
//                centerPoints[i] = tempPoints[i];
//            }
//        }
//        return true;
//
//        if(!isNeededContinueFlag){
//            return false;
//        }
//    }

    /**
     * 初始化中心点
     */
    public void initCenterPoint(){

        //随机取 N 个点,作为N个簇的初始点
        for(int i = 0; i < kinds; i++){
            centerPoints[i] = new Random().nextInt(totolPoints.size()-2) + 2; // 生成 ID 为 2 ~ totolPoints.size()-1 之间的随机数

            //将初始ID加入禁忌表,并设置禁忌长度
            tabuList.put(centerPoints[i],tabuSize);
        }
    }


    /**
     * 初始化预读数据
     * @param path
     */
    public void dataPreprocessing(String path){
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

    }

}
