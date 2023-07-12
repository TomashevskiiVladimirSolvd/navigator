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

import org.example.service.implementation.PointService;


import java.util.List;


public class App {
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
        }

        scan.close();


        int id = 7; // change ID number to test
        Point point = pointService.getPoint(id);
        if (point != null) {
            pointService.delete(point);
            logger.info("Deleted point with ID #" + id);
        } else
            logger.info("Point with ID #" + id + " does not exist in database");



    }
}
