package com.kyle.pojo;

/**
 * Created by Java on 2016/11/14.
 */
public class Face implements Comparable<Face>{
    private String faceid;
    private int ageValue;
    private int ageRange;
    private String genderValue;
    private double genderConfident;
    private String raceValue;
    private double raceConfident;
    private double smilingValue;
    private double centerX;
    private double centerY;

    public String getFaceid() {
        return faceid;
    }

    public void setFaceid(String faceid) {
        this.faceid = faceid;
    }

    public int getAgeValue() {
        return ageValue;
    }

    public void setAgeValue(int ageValue) {
        this.ageValue = ageValue;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public double getGenderConfident() {
        return genderConfident;
    }

    public void setGenderConfident(double genderConfident) {
        this.genderConfident = genderConfident;
    }

    public String getRaceValue() {
        return raceValue;
    }

    public void setRaceValue(String raceValue) {
        this.raceValue = raceValue;
    }

    public double getRaceConfident() {
        return raceConfident;
    }

    public void setRaceConfident(double raceConfident) {
        this.raceConfident = raceConfident;
    }

    public double getSmilingValue() {
        return smilingValue;
    }

    public void setSmilingValue(double smilingValue) {
        this.smilingValue = smilingValue;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    @Override
    public int compareTo(Face face) {
        int result=0;
        if(this.getCenterX()>face.getCenterX()){
            result=1;
        }else{
            result=-1;
        }
        return result;
    }
}
