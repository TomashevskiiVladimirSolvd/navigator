package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.example.model.Observer.DesiredPath;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.User;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.RouteDAO;
import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final Logger logger = Logger.getLogger("GLOBAL");

    public static void main( String[] args ) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        PointService pointService = new PointService();

        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);
        /**
        List<Point> points = Stream.generate(() -> pointService.create(randomPointsGenerator.createRandomPoint()))
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(points);
         **/

        //Observer logic
        User ourMentor = new User("Andrei", "Trukhanovich", "atrukhanovich@solvd.com");
        DesiredPath routing = new DesiredPath();
        routing.subscribe(ourMentor);
        Route route = new Route();
        routing.setRoute(route);
        ourMentor.setDesiredPath(routing);
        // calculate the optimal route and then set that info to route
        routing.notifyUsers();
        /**
        logger.info("*** GENERATED POINTS ***");
        for (Point point : points)
            logger.info(point);

        List<Point> allPoints = pointService.getPoints();
        logger.info("*** POINTS IN DATABASE ***");
        for (Point point : allPoints)
            logger.info(point);

        int id = 5; // change ID number to test
        Point point = pointService.getPoint(id);
        if (point != null) {
            pointService.delete(point);
            logger.info("Deleted point with ID #" + id);
        } else
            logger.info("Point with ID #" + id + " does not exist in database");
         **/
    }
}
