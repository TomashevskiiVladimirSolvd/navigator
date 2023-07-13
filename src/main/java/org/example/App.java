package org.example;



import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.example.model.Route;
import org.example.model.Point;


import org.apache.ibatis.session.SqlSession;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.config.Configurator;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.RouteDAO;
import org.example.model.Observer.DesiredPath;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.User;
import org.example.model.builder.RouteBuilder;
import org.example.model.builder.UserBuilder;

import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;


import java.util.ArrayList;

import java.util.List;


public class App {
    //private static final Logger logger = LogManager.getLogger("APP");
    private static final Logger logger = Logger.getLogger("GLOBAL");

    public static void main( String[] args ) {


        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        //point service
        PointService pointService = new PointService();

        // Get start and end coordinates from command line arguments
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for their first name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        // Prompt the user for their last name
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // Print the greeting with the provided name
        System.out.println("Hello, " + firstName + " " + lastName + "!");







        //Configurator.initialize(null, "src/main/resources/log4j2.xml");
        //PointService pointService = new PointService();




        /**

        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);

        List<Point> points = Stream.generate(() -> pointService.create(randomPointsGenerator.createRandomPoint()))
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(points);


        //Observer logic
//        User ourMentor = new UserBuilder()
//                .setName("Andrei")
//                .setSurname("Trukhanovich")
//                .setEmail("atrukhanovich@solvd.com")
//                .getUser();
//        DesiredPath routing = new DesiredPath();
//        routing.subscribe(ourMentor);
//        Route routes = new Route();
//        routing.setRoute(routes);
//        ourMentor.setDesiredPath(routing);
//        // calculate the optimal route and then set that info to route
//        routing.notifyUsers();

//        logger.info("*** GENERATED POINTS ***");
//        for (Point point : points)
//            logger.info(point);


        List<Point> allPoints = pointService.getPoints();
        logger.info("*** POINTS IN DATABASE ***");
        for (Point point : allPoints)
            logger.info(point);



        RandomPointsGenerator r = new RandomPointsGenerator();

        // Generate 5 random points
        List<Point> randomPoints = r.getRandomPoints(allPoints, 5);

        // Print the random points
        logger.info("*** RANDOM POINTS ***");
        for (Point point : randomPoints) {
            logger.info(point);
        }

        List<Route> route = new ArrayList<>();
        route.add(new Route(allPoints.get(0), allPoints.get(1), 3));
        route.add(new Route(allPoints.get(1), allPoints.get(2), 4));
        route.add(new Route(allPoints.get(2), allPoints.get(3), 5));


        System.out.println(allPoints.get(0));
        System.out.println(allPoints.get(1));
        System.out.println(allPoints.get(2));
        System.out.println(allPoints.get(3));


        Scanner scan = new Scanner(System.in);

        // Start the program loop
        boolean exit = false;

        while (!exit) {
            // Get start and end coordinates from user input
            System.out.print("Enter the x-coordinate of the start point (or 'exit' to quit): ");
            String startXInput = scan.next();

            // Check if user wants to exit
            if (startXInput.equalsIgnoreCase("exit")) {
                exit = true;
                continue;
            }

            double startXC = Double.parseDouble(startXInput);

            System.out.print("Enter the y-coordinate of the start point: ");
            double startYC = scan.nextDouble();
            System.out.print("Enter the x-coordinate of the end point: ");
            double endXC = scan.nextDouble();
            System.out.print("Enter the y-coordinate of the end point: ");
            double endYC = scan.nextDouble();

            Point starts = null;
            Point ends = null;

            // Find the start and end points
            for (Point point : allPoints) {
                if (point.getXCoordinate() == startXC && point.getYCoordinate() == startYC) {
                    starts = point;
                }
                if (point.getXCoordinate() == endXC && point.getYCoordinate() == endYC) {
                    ends = point;
                }
            }

            // Create the calculator instance
            ShortestPathCalculator cal = new ShortestPathCalculator(allPoints, route);

            // Calculate the shortest path
            long shortestP = cal.calculateShortestPath(starts, ends);

            if (shortestP == -1) {
                System.out.println("No path found from (" + startXC + ", " + startYC + ") to (" + endXC + ", " + endYC + ")");
            } else {
                System.out.println("Shortest path from (" + startXC + ", " + startYC + ") to (" + endXC + ", " + endYC + ") is " + shortestP);
            }

            // Get the points between start and end
            List<Point> pointsBetween = cal.getPointsBetween(starts, ends);
            if (!pointsBetween.isEmpty()) {
                System.out.println("Points between (" + startXC + ", " + startYC + ") and (" + endXC + ", " + endYC + "):");
                for (Point point : pointsBetween) {
                    System.out.println(point);
                }
            }

            // Get the route history
            List<Route> routeHistory = cal.getRouteHistory(starts, ends);
            if (!routeHistory.isEmpty()) {
                System.out.println("Route history from (" + startXC + ", " + startYC + ") to (" + endXC + ", " + endYC + "):");
                for (Route rout : routeHistory) {
                    System.out.println(rout);
                }
            }
        }

        scan.close();


       

        int id = 5; // change ID number to test

        Point point = pointService.getPoint(id);
        if (point != null) {
            pointService.delete(point);
            logger.info("Deleted point with ID #" + id);
        } else
            logger.info("Point with ID #" + id + " does not exist in database");


//        SqlSession session = MyBatisSession.getSqlSession();
//        RouteDAO routeMapper = session.getMapper(RouteDAO.class);
//        Route route1 = routeMapper.getRoute(1);
//
//
//
//        Point point1 = pointService.create(allPoints.get(0));
//        logger.info("A new point has been added into the database: " + point1);
//
//        RouteService routeService = new RouteService();
//        Route route2 = new Route(allPoints.get(0), allPoints.get(1), 100500);
//        routeService.create(route1);
//        logger.info("A new route without waypoints has been added into the database: " + route1);
//
//        List<Point> wayPoints = new ArrayList<>();
//        wayPoints.add(allPoints.get(3));
//        Route route3 = new Route(allPoints.get(4), allPoints.get(5), 500100, wayPoints);
//        routeService.create(route2);
//        logger.info("A new route with waypoints has been added into the database: " + route2);

        SqlSession session = MyBatisSession.getSqlSession();
        RouteDAO routeMapper = session.getMapper(RouteDAO.class);
        Route route1 = routeMapper.getRoute(1);



        Point point1 = pointService.create(allPoints.get(0));
        logger.info("A new point has been added into the database: " + point1);

        **/

//        RouteService routeService = new RouteService();
//        Route route2 = new Route(pointService.getPoint(1), pointService.getPoint(2), 100500);
//        routeService.create(route2);
//        logger.info("A new route without waypoints has been added into the database: " + routeService.getRoute(route2.getId()));
//
//        List<Point> wayPoints = new ArrayList<>();
//
//        wayPoints.add(allPoints.get(3));
//        Route route3 = new Route(allPoints.get(4), allPoints.get(5), 500100, wayPoints);
//        routeService.create(route2);
//        logger.info("A new route with waypoints has been added into the database: " + route2);
//
//
//
//        wayPoints.add(pointService.getPoint(3));
//        wayPoints.add(pointService.getPoint(4));
//        Route route3 = new Route(pointService.getPoint(6), pointService.getPoint(8), 500100, wayPoints);
//        routeService.create(route3);
//        logger.info("A new route without waypoints has been added into the database: " + routeService.getRoute(route3.getId()));

    }


}
