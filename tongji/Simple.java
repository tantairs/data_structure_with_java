package com.lianxi.tongji;

/**
 * Created by tantairs on 16/3/6.
 */
public class Simple {
    private static int features = 23351;
    double[] feature = new double[features];
    int id = 0;
    String name = null;

    public Simple(){

    }

    public Simple(int id, String name){

        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double[] getFeature() {
        return feature;
    }

    public void setFeature(double[] feature2) {
        for(int i = 0; i < feature2.length;i++){
            feature[i] = feature2[i];
        }
    }
}
