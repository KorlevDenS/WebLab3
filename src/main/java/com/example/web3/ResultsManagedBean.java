package com.example.web3;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named ("resBean")
@SessionScoped
public class ResultsManagedBean implements Serializable {
    private List<ReadyPoint> resultPointsList;
    private Point newPoint = new Point();


    public ResultsManagedBean() {
        this.resultPointsList = new ArrayList<>();
    }

    public ResultsManagedBean(ArrayList<ReadyPoint> points) {
        this.resultPointsList = points;
    }

    public List<ReadyPoint> loadPointsFromDB() {
        List<Point> points;
        List<ReadyPoint> readyPoints = new ArrayList<>();

        PointService pointService = new PointService();
        points = pointService.getAllPoints();


        for (Point point : points) {
            if (point.getR1()) readyPoints.add(new ReadyPoint(point, Point.rArray[1]));
            if (point.getR2()) readyPoints.add(new ReadyPoint(point, Point.rArray[2]));
            if (point.getR3()) readyPoints.add(new ReadyPoint(point, Point.rArray[3]));
            if (point.getR4()) readyPoints.add(new ReadyPoint(point, Point.rArray[4]));
            if (point.getR5()) readyPoints.add(new ReadyPoint(point, Point.rArray[5]));
        }


        return readyPoints;
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
            if (this.newPoint.getR1() || this.newPoint.getR2() || this.newPoint.getR3()
            || this.newPoint.getR4() || this.newPoint.getR5()) pointService.savePoint(this.newPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.newPoint.getR1()) resultPointsList.add(0, new ReadyPoint(this.newPoint, Point.rArray[1]));
        if (this.newPoint.getR2()) resultPointsList.add(0, new ReadyPoint(this.newPoint, Point.rArray[2]));
        if (this.newPoint.getR3()) resultPointsList.add(0, new ReadyPoint(this.newPoint, Point.rArray[3]));
        if (this.newPoint.getR4()) resultPointsList.add(0, new ReadyPoint(this.newPoint, Point.rArray[4]));
        if (this.newPoint.getR5()) resultPointsList.add(0, new ReadyPoint(this.newPoint, Point.rArray[5]));


        this.newPoint = new Point();
    }


    public List<ReadyPoint> getResultPointsList() {
        return resultPointsList;
    }

    public void setResultPointsList(List<ReadyPoint> resultPointsList) {
        this.resultPointsList = resultPointsList;
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }
    public Point getNewPoint() {
        return newPoint;
    }
}
