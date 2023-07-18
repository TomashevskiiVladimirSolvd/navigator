package org.example;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.ListCellRenderer;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.RouteDAO;
import org.example.model.Route;
import org.example.model.Point;

import org.example.model.User;
import org.example.model.builder.RouteBuilder;
import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        boolean continueProgram = true;
        List<String> calculationHistory = new ArrayList<>();
        User currentUser;

        PointService pointService = new PointService();
        RouteService routeService = new RouteService();

        System.out.println("\n*** Thank you for waiting while the app is loading ***");

        List<Route> allRoutes = routeService.getRoutes();

        logger.info("*** ROUTES IN DATABASE ***");
        for (Route rou : allRoutes)
            logger.info(rou);

        List<Point> allPoints = pointService.getPoints();

        if (allPoints.isEmpty()) {
            int cityNumber = 1;
            RandomPointsGenerator pointGenerator = new RandomPointsGenerator(0, 100, 0, 100, 10);
            allPoints = Stream.generate(() -> pointService.create(pointGenerator.createRandomPoint())).limit(pointGenerator.getNumPoints()).collect(
                    Collectors.toList());
        }




        //random points would be generated into the database
        RandomPointsGenerator r = new RandomPointsGenerator();

        List<Point> ppp = Stream.generate(() -> pointService.create(r.createRandomPoint())). limit(r.getNumPoints()).collect(
            Collectors.toList());

        List<Point> randomPoints = r.getRandomPoints(allPoints, 5);
        List<String> cities = new ArrayList<>();

        int cityNumber = 1;
        for (Point point : randomPoints) {
            String city = r.generateCityName(cityNumber);
            point.setCityName(city);
            pointService.create(point);
            cities.add(city);
            cityNumber++;
        }

        logger.info("Random points");
        for (Point p : randomPoints){
            logger.info(p.getId());
            logger.info(p);
        }



        Point lastEndPoint = null;

        List<Route> row = new ArrayList<>();


        randomPoints.sort(Comparator.comparingDouble(Point::getXCoordinate));

        // Loop through each pair of points and calculate the distance
        for (int i = 0; i < allPoints.size(); i++) {
            Point p1 = allPoints.get(i);

            for (int j = i + 1; j < allPoints.size(); j++) {
                Point p2 = allPoints.get(j);
                double distance = r.calculateDistance(p1, p2);
                System.out.println("Distance between point " + i + " and point " + j + ": " + (long) distance);
            }
        }





//        for (Point point : randomPoints) {
//            if (lastEndPoint != null) {
//                double distance = RandomPointsGenerator.calculateDistance(lastEndPoint, point);
//                Route route = new Route(lastEndPoint, point, (long) distance);
//                row.add(route);
//                routeService.create(route);
//                System.out.println(route);
//            }
//            lastEndPoint = point;
//        }
//
//
//        for (Route route : row) {
//            System.out.println(route);
//        }



//        for (Route route : row) {
            //System.out.println(route); //null id
//            routeService.create(route);
//            System.out.println(route); //id associated
//        }





        Scanner scan = new Scanner(System.in);

        System.out.println("\n✦✦✦ WELCOME TO NAVIGATOR ✦✦✦");
        System.out.println("If you are a new user, sign up!");
        System.out.println("If you are a returning user, please log in.");

        while (continueProgram) {
            UserRegistration.start();
            currentUser = UserRegistration.getCurrentUser();

            boolean continueMenu = true;
            while (continueMenu) {
                System.out.println("✦✦✦ WHAT WOULD YOU LIKE DO? ✦✦✦");
                System.out.println("[ 1 ] Search for a route");
                System.out.println("[ 2 ] Log out");
                System.out.println("[ 3 ] Exit");
                System.out.print("Enter a selection: ");
                String input = scan.next();

                switch (input) {
                    case "1":
                        System.out.println("\nWhere would you like to go?");
                        System.out.println("Select from the list of available cities below.");

                        System.out.println("\nLoading cities...");

                        System.out.println("\n✦✦✦ LIST OF CITIES ✦✦✦");
                        for (Point point : allPoints)
                            System.out.println("[ " + point.getId() + " ] " + point.getCityName());

                        // Start the program loop
                        boolean exit = false;
                        while (!exit) {
                            // Get start and end coordinates from user input
                            System.out.println("\nPlease set your location and destination.");
                            System.out.println("Enter 'exit' if you would like to quit.\n");

                            String startXInput = null;
                            boolean startIdInvalid = true;
                            while (startIdInvalid) {
                                // Check if user wants to exit
                                System.out.print("Enter the ID of the start point: ");
                                startXInput = scan.next();

                                if (startXInput.equalsIgnoreCase("exit")) {
                                    System.out.println();
                                    exit = true;
                                    break;
                                } else if (!NumberUtils.isNumber(startXInput)) {
                                    System.out.println("ID must be numeric or enter 'exit' to quit.");
                                } else
                                    startIdInvalid = false;
                            }
                            if (exit)
                                break;

                            int startXC = Integer.parseInt(startXInput);

                            String endYInput = null;
                            boolean endIdInvalid = true;
                            while (endIdInvalid) {
                                // Check if user wants to exit
                                System.out.print("Enter the ID of the end point: ");
                                endYInput = scan.next();

                                if (endYInput.equalsIgnoreCase("exit")) {
                                    System.out.println();
                                    exit = true;
                                    break;
                                } else if (!NumberUtils.isNumber(endYInput)) {
                                    System.out.println("ID must be numeric or enter 'exit' to quit.");
                                } else
                                    endIdInvalid = false;
                            }
                            if (exit)
                                break;

                            int endYC = Integer.parseInt(endYInput);

                            Point starts = pointService.getPoint(startXC);
                            Point ends = pointService.getPoint(endYC);

                            // Check if start or end points were not found
                            if (starts == null || ends == null) {
                                System.out.println("Invalid start or end point ID.");
                                continue;
                            }

                            // Create the calculator instance
                            ShortestPathCalculator cal = new ShortestPathCalculator(allPoints, allRoutes);

                            boolean routeExists = false;
                            for (Route route : allRoutes) {
                                if (route.getStartPoint().equals(starts) && route.getEndPoint().equals(ends)) {
                                    System.out.println(route);

                                    System.out.println("\nRetrieving shortest route...\n");

                                    System.out.print("Enter the desired unit (miles or km): ");
                                    String unit = scan.next();

                                    if (unit.equalsIgnoreCase("miles")) {
                                        String calculationResult = "Shortest path from (" + route.getStartPoint().getId() + ") to (" + route.getEndPoint().getId() + ") is " + ShortestPathCalculator.kilometersToMiles(route.getDistance()) + " miles";
                                        System.out.println("\n" + calculationResult);
                                        calculationHistory.add(calculationResult);
                                    } else if (unit.equalsIgnoreCase("km")) {
                                        String calculationResult = "Shortest path from (" + route.getStartPoint().getId() + ") to (" + route.getEndPoint().getId() + ") is " + route.getDistance() + " km";
                                        System.out.println("\n" + calculationResult);
                                        calculationHistory.add(calculationResult);
                                    } else {
                                        System.out.println("Invalid unit. Route value will be displayed in default units.");
                                        String calculationResult = "Shortest path from (" + route.getStartPoint().getId() + ") to (" + route.getEndPoint().getId() + ") is " + route.getDistance() + " km";
                                        System.out.println("\n" + calculationResult);
                                        calculationHistory.add(calculationResult);
                                    }

                                    System.out.println("Points between (" + route.getStartPoint().getId() + ") and (" + route.getEndPoint().getId() + "):");
                                    System.out.print("(" + route.getStartPoint().getCityName() + ") ---> ");
                                    List<Point> pointsBetween = route.getWayPoints();
                                    for (Point point : pointsBetween) {
                                        System.out.print("(" + point.getCityName() + ") ---> ");
                                    }
                                    System.out.print("(" + route.getEndPoint().getCityName() + ")");
                                    System.out.println("\n");
                                    routeExists = true;
                                    break;
                                }
                            }

                            if (!routeExists) {
                                System.out.println("\nCalculating shortest route...\n");
                                RouteBuilder routeBuilder = new RouteBuilder();
                                List<Point> pointsBetween = cal.getPointsBetween(starts, ends);

                                long shortestP = cal.calculateShortestPath(starts, ends);

                                if (shortestP == -1) {
                                    System.out.println("No path found from (" + starts.getId() + ") to (" + ends.getId() + ")");
                                } else {
                                    System.out.print("Enter the desired unit (miles or km): ");
                                    String unit = scan.next();

                                    if (unit.equalsIgnoreCase("miles")) {
                                        String calculationResult = "Shortest path from (" + starts.getId() + ") to (" + ends.getId() + ") is " + ShortestPathCalculator.kilometersToMiles(shortestP) + " miles";
                                        System.out.println("\n" + calculationResult);
                                        calculationHistory.add(calculationResult);
                                    } else if (unit.equalsIgnoreCase("km")) {
                                        String calculationResult = "Shortest path from (" + starts.getId() + ") to (" + ends.getId() + ") is " + shortestP + " km";
                                        System.out.println("\n" + calculationResult);
                                        calculationHistory.add(calculationResult);
                                    } else {
                                        System.out.println("Invalid unit. Route value will be displayed in default units.");
                                        String calculationResult = "Shortest path from (" + starts.getId() + ") to (" + ends.getId() + ") is " + shortestP + " km";
                                        System.out.println("\n" + calculationResult);
                                        calculationHistory.add(calculationResult);
                                    }
                                }

                                Route route = routeBuilder.setStartPoint(starts)
                                        .setEndPoint(ends)
                                        .setDistance(shortestP)
                                        .setWayPoints(pointsBetween).getRoute();

                                routeService.create(route);

                                System.out.println(route);

                                System.out.println("Points between (" + starts.getId() + ") and (" + ends.getId() + "):");
                                System.out.print("(" + starts.getCityName() + ") ---> ");
                                for (Point point : pointsBetween) {
                                    System.out.print("(" + point.getCityName() + ") ---> ");
                                }
                                System.out.print("(" + ends.getCityName() + ")");
                                System.out.println("\n");
                            }

                            // Displays each point object in the list
                            for (Point point : cal.getPointsBetween(starts, ends))
                                System.out.println(point);
                            System.out.println();

                            // Get the route itinerary
                            List<Route> routeHistory = cal.getRouteHistory(starts, ends);
                            if (!routeHistory.isEmpty()) {
                                System.out.println("Route itinerary from (" + startXC + ") to (" + endYC + "):");
                                for (Route rout : routeHistory) {
                                    System.out.println(rout);
                                }
                            }
                            System.out.println();
                            exit = true;
                        }
                        break;
                    case "2":
                        System.out.println("\n✨✨✨ Thank you for using Navigator, come back soon! ✨✨✨");
                        UserRegistration.start();
                        currentUser = UserRegistration.getCurrentUser();
                        break;
                    case "3":
                        continueMenu = false;
                        continueProgram = false;
                        break;
                    default:
                        System.out.println("\n*** Sorry, that is not a valid option. Please choose from the menu below. ***\n");
                        break;
                }
            }
        }

        scan.close();

        System.out.println("\nCalculation History:");
        for (String calculation : calculationHistory) {
            System.out.println(calculation);
        }

        System.out.println("\n✨✨✨ Thank you for using Navigator! ✨✨✨\n");

    }
}
