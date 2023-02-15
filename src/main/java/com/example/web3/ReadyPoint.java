package com.example.web3;



import java.io.Serializable;

public class ReadyPoint implements Serializable {

    private double x;
    private double y;
    private double r;
    private String result;
    private double executingTime;
    private String currentTime;

    private String checkedR;

    public ReadyPoint() {
    }

    public ReadyPoint(Point point, double r) {
        this.x = point.getX();
        this.y = point.getY();
        this.r = r;
        this.currentTime = point.getCurrentTime();
        this.executingTime = point.getExecutingTime();
        this.result = point.getResult();
        this.checkedR = formChecked(point);
    }

    private String formChecked(Point point) {
        String answer = "";
        if (point.getR1()) answer += "1 ";
        if (point.getR2()) answer += "2 ";
        if (point.getR3()) answer += "3 ";
        if (point.getR4()) answer += "4 ";
        if (point.getR5()) answer += "5 ";
        return answer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getExecutingTime() {
        return executingTime;
    }

    public void setExecutingTime(double executingTime) {
        this.executingTime = executingTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCheckedR() {
        return checkedR;
    }

    public void setCheckedR(String checkedR) {
        this.checkedR = checkedR;
    }
}
