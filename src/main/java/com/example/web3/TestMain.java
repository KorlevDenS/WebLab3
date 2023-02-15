package com.example.web3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestMain {

    public static void main(String[] args) {

        PointService pointService = new PointService();
        Point point = new Point(12, 2, true, true, true, true,true);
        Point point1 = new Point(4, 3, true, true, true, true,true);
        Point point2 = new Point(0, 1, true, true, true, true,true);

        //pointService.savePoint(point);
        //pointService.savePoint(point1);
        //pointService.savePoint(point2);
        //pointService.clearAllPoints();
        //System.out.println(pointService.getAllPoints());
        pointService.clearAllPoints();

        //for (Point p : pointService.getAllPoints()) System.out.println(p);

            /*
            UserService userService = new UserService();
            User user = new User("Masha",26);
            userService.saveUser(user);
            Auto ferrari = new Auto("Ferrari", "red");
            ferrari.setUser(user);
            user.addAuto(ferrari);
            Auto ford = new Auto("Ford", "black");
            ford.setUser(user);
            user.addAuto(ford);
            userService.updateUser(user);*/
    }
}
