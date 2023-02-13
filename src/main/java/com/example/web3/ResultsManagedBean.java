package com.example.web3;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ManagedBean (name = "resBean")
@SessionScoped
public class ResultsManagedBean implements Serializable {
    private List<Point> resultPointsList;
    private Point newPoint = new Point();


    public ResultsManagedBean() {
        this.resultPointsList = new ArrayList<>();
    }

    public ResultsManagedBean(ArrayList<Point> points) {
        this.resultPointsList = points;
    }

    public List<Point> loadPointsFromDB() {
        List<Point> points;
        PointService pointService = new PointService();
        points = pointService.getAllPoints();
        return points;
    }

    @PostConstruct
    private void loadPoints() {
        this.resultPointsList = loadPointsFromDB();
    }

    public void addPoint() {
        PointService pointService = new PointService();
        long startTime = System.nanoTime();
        this.newPoint.checkHidden();
        this.newPoint.checkHit();
        this.newPoint.setCurrentTime(LocalDateTime.now());
        this.newPoint.setExecutingTime((System.nanoTime() - startTime) / 1000000d);
        try {
            pointService.savePoint(this.newPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.resultPointsList.add(0, this.newPoint);
        this.newPoint = new Point();
    }


    public List<Point> getResultPointsList() {
        return resultPointsList;
    }

    public void setResultPointsList(List<Point> resultPointsList) {
        this.resultPointsList = resultPointsList;
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }
    public Point getNewPoint() {
        return newPoint;
    }
}
