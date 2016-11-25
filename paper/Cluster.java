package com.lianxi.paper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantairs on 9/29/16.
 */
public class Cluster {
    int centerId = 0;
    int pointNumbers = 0;


    List<Point> list = new ArrayList<>();

    public List<Point> getList() {
        return list;
    }

    public void setList(List<Point> list) {
        this.list = list;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public int getPointNumbers() {
        return pointNumbers;
    }

    public void setPointNumbers(int pointNumbers) {
        this.pointNumbers = pointNumbers;
    }
}
