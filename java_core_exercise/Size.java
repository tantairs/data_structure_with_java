package com.lianxi.java_core_exercise;

/**
 * Created by tantairs on 15/12/13.
 */
public enum Size {
    SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
    private Size(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
    private String abbreviation;
}
