package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.model.Route;
import org.example.model.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.model.User;
import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.List;

public class App {
    private static final Logger logger = LogManager.getLogger("APP");

    public static void main(String[] args) {
        User user = UserRegistration.start(); // returns the current user of the program after registration
        // add other app implementation below here
        // if you want to receive information about the user, use the user object above
        System.out.println("\nWhere would you like to go?");
        System.out.println("Select from the list of available cities below.");
        System.out.println("Loading cities...");

        PointService pointService = new PointService();
        RouteService routeService = new RouteService();

        List<Route> allRoutes = routeService.getRoutes();
        for (Route rou : allRoutes);

        List<Point> allPoints = pointService.getPoints();
        System.out.println("\n✦✦✦ LIST OF CITIES ✦✦✦");
        for (Point point : allPoints)
            System.out.println("[ " + point.getId() + " ] " + point.getCityName());


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

            // Find the start and end points
            for (Point point : allPoints) {
                if (point.getId() == startXC) {
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
                System.out.println("No path found from (" + startXC + ") to ( " + endYC + ")");
            } else {
                System.out.print("Enter the desired unit (miles or km): ");
                String unit = scan.next();

                if (unit.equalsIgnoreCase("miles")) {
                    shortestP = cal.kilometersToMiles(shortestP);
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " miles");
                } else if (unit.equalsIgnoreCase("km")) {
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km");
                } else {
                    System.out.println("Invalid unit. Route value will be displayed in default units.");
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km");
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
                System.out.println("Route history from (" + startXC + ") to (" + endYC + "):");
                for (Route rout : routeHistory) {
                    System.out.println(rout);
                }
            }
        }

        scan.close();


    }


}