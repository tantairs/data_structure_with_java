package com.lianxi.paper;

/**
 * Created by tantairs on 9/29/16.
 */
public class Point {

    public Point(){

    }

    public Point(int n){
        dimension = n;
        data = new double[dimension];
    }

    private int dimension;
    private double[] data;
    private int id;
    private int artificialKind;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtificialKind() {
        return artificialKind;
    }

    public void setArtificialKind(int artificialKind) {
        this.artificialKind = artificialKind;
    }

}
