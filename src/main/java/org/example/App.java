package org.example;


import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.RouteDAO;
import org.example.model.Observer.DesiredPath;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.User;
import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final Logger logger = LogManager.getLogger("APP");

    public static void main( String[] args ) {
        Configurator.initialize(null, "src/main/resources/log4j2.xml");
        PointService pointService = new PointService();

        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);

        List<Point> points = Stream.generate(() -> pointService.create(randomPointsGenerator.createRandomPoint()))
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(points);

        //Observer logic
        User ourMentor = new User("Andrei", "Trukhanovich", "atrukhanovich@solvd.com");
        DesiredPath routing = new DesiredPath();
        routing.subscribe(ourMentor);
        Route route = new Route();
        routing.setRoute(route);
        ourMentor.setDesiredPath(routing);
        // calculate the optimal route and then set that info to route
        routing.notifyUsers();

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

        SqlSession session = MyBatisSession.getSqlSession();
        RouteDAO routeMapper = session.getMapper(RouteDAO.class);
        Route route1 = routeMapper.getRoute(1);

        Point point1 = pointService.create(allPoints.get(0));
        logger.info("A new point has been added into the database: " + point1);

        RouteService routeService = new RouteService();
        Route route2 = new Route(allPoints.get(0), allPoints.get(1), 100500);
        routeService.create(route2);
        logger.info("A new route without waypoints has been added into the database: " + routeService.getRoute(route2.getId()));

        List<Point> wayPoints = new ArrayList<>();
        wayPoints.add(allPoints.get(3));
        Route route3 = new Route(allPoints.get(4), allPoints.get(5), 500100, wayPoints);
        routeService.create(route3);
        logger.info("A new route without waypoints has been added into the database: " + routeService.getRoute(route3.getId()));
    }


}
