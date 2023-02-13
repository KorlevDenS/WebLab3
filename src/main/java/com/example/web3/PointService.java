package com.example.web3;

import java.util.List;

public class PointService {
    private final PointDataAccessObject pointDao = new PointDataAccessObject();

    public PointService() {

    }

    public void savePoint(Point point) {
        pointDao.save(point);
    }

    public Point findPointById(int id) {
        return pointDao.findById(id);
    }

    public void deletePoint(Point point) {
        pointDao.delete(point);
    }

    public List<Point> getAllPoints() {
        return pointDao.getAll();
    }

    public void clearAllPoints() {
        pointDao.clearAll();
    }

}
