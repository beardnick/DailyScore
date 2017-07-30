package com.example.asus.dailyscore.DataClass;

import java.util.Date;

/**
 * Created by asus on 2017/7/29.
 */

public class Hobby {
    private String name;
    private int perScore;
    private Date beginDate;
    private Date date;
    private int score;
    private int counter;
    public Hobby(String name,int perScore,Date beginDate){
        this.beginDate = beginDate;
        this.perScore = perScore;
        this.name = name;
        this.date = new Date(System.currentTimeMillis());
        this.score = 0;
        this.counter=0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName(){

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerScore() {
        return perScore;
    }

    public int getScore() {
        return score;
    }

    public java.util.Date getDate() {

        return date;
    }

    public java.util.Date getBeginDate() {

        return beginDate;
    }

    public void setPerScore(int perScore) {
        this.perScore = perScore;
    }
    public void calculateScore(){
        this.score=perScore*counter;
    }
}

