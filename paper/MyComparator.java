package com.lianxi.paper;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by tantairs on 2016/11/25.
 */
public class MyComparator implements Comparator<Map.Entry<Point,Double>> {

    @Override
    public int compare(Map.Entry<Point, Double> o1, Map.Entry<Point, Double> o2) {
        return new Double(o1.getValue()).compareTo(o2.getValue());
    }
}
