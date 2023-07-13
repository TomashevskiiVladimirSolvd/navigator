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
        RouteService routeService = new RouteService();



        // Get start and end coordinates from command line arguments
        Scanner scanner = new Scanner(System.in);

        String intro = "======\033[0;1mWelcome to the Shortest Path App\033[0m======\n";

        System.out.print(intro);

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







        List<Route> allRoutes = routeService.getRoutes();
        logger.info("*** ROUTES IN DATABASE ***");
        for (Route rou : allRoutes)
            logger.info(rou);

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



        Scanner scan = new Scanner(System.in);

        // Start the program loop
        boolean exit = false;

        while (!exit) {
            // Get start and end coordinates from user input
            System.out.print("Enter the ID of the start point (or 'exit' to quit): ");
            String startXInput = scan.next();

            // Check if user wants to exit
            if (startXInput.equalsIgnoreCase("exit")) {
                exit = true;
                continue;
            }

            int startXC = Integer.parseInt(startXInput);

            System.out.print("Enter the ID of the end point: ");
            int endYC = scan.nextInt();

            Point starts = null;
            Point ends = null;
//            Point starts = new Point(1);
//            Point ends = new Point(2);

            // Find the start and end points
            for (Point point : allPoints) {
                if (point.getId() == startXC ) {
                    starts = point;
                }
                if (point.getId() == endYC) {
                    ends = point;
                }
            }

            // Check if start or end points were not found
            if (starts == null || ends == null) {
                System.out.println("Invalid start or end point ID.");
                continue;
            }

            // Create the calculator instance
            ShortestPathCalculator cal = new ShortestPathCalculator(allPoints, allRoutes);

            // Calculate the shortest path
            long shortestP = cal.calculateShortestPath(starts, ends);

            if (shortestP == -1) {
                System.out.println("No path found from (" + startXC +  ") to ( " + endYC + ")");
            } else {
                System.out.print("Enter the desired unit (miles or km): ");
                String unit = scan.next();

                if (unit.equalsIgnoreCase("miles")) {
                    shortestP = cal.kilometersToMiles(shortestP);
                    System.out.println("Shortest path from (" + startXC +  ") to ( " + endYC + ") is " + shortestP + " miles");
                } else if (unit.equalsIgnoreCase("km")) {
                    System.out.println("Shortest path from (" + startXC + ") to ( " + endYC + ") is " + shortestP + " km");
                } else {
                    System.out.println("Invalid unit. Route value will be displayed in default units.");
                    System.out.println("Shortest path from (" + startXC +  ") to ( " + endYC + ") is " + shortestP + " km");
                }
            }

            // Get the points between start and end
            List<Point> pointsBetween = cal.getPointsBetween(starts, ends);
            if (!pointsBetween.isEmpty()) {
                System.out.println("Points between (" + startXC + ") and (" + endYC + "):");
                for (Point point : pointsBetween) {
                    System.out.println(point);
                }
            }

            // Get the route history
            List<Route> routeHistory = cal.getRouteHistory(starts, ends);
            if (!routeHistory.isEmpty()) {
                System.out.println("Route history from (" + startXC + ") to ( " + endYC + "):");
                for (Route rout : routeHistory) {
                    System.out.println(rout);
                }
            }
        }

        scan.close();




    }


}
