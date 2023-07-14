package org.example;


import java.util.ArrayList;
import java.util.Scanner;


import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Route;
import org.example.model.Point;

import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.List;

public class App {
    private static final Logger logger = LogManager.getLogger("APP");

    public static void main(String[] args) {

        System.out.println("\n✦✦✦ WELCOME TO NAVIGATOR ✦✦✦");
        System.out.println("If you are a new user, sign up!");
        System.out.println("If you are a returning user, please log in.");

        UserRegistration.start(); // returns the current user of the program after registration
        // add other app implementation below here
        // if you want to receive information about the user, use the user object above
        System.out.println("Where would you like to go?");
        System.out.println("Select from the list of available cities below.");

        PointService pointService = new PointService();
        RouteService routeService = new RouteService();


        List<Route> allRoutes = routeService.getRoutes();

        List<Point> allPoints = pointService.getPoints();
        System.out.println("\n✦✦✦ LIST OF CITIES ✦✦✦");
        for (Point point : allPoints)
            System.out.println("[ " + point.getId() + " ] " + point.getCityName());


        Scanner scan = new Scanner(System.in);

        List<String> calculationHistory = new ArrayList<>();

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
                    exit = true;
                    break;
                } else if (!NumberUtils.isNumber(endYInput)) {
                    System.out.println("ID must be numeric.");
                } else
                    endIdInvalid = false;
            }
            if (exit)
                break;

            int endYC = Integer.parseInt(endYInput);

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
                System.out.println("No path found from (" + startXC + ") to (" + endYC + ")");
            } else {
                System.out.print("Enter the desired unit (miles or km): ");
                String unit = scan.next();

                if (unit.equalsIgnoreCase("miles")) {
                    shortestP = cal.kilometersToMiles(shortestP);
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " miles");
                    String calculationResult = "Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " miles";
                    calculationHistory.add(calculationResult);
                } else if (unit.equalsIgnoreCase("km")) {
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km");
                    String calculationResult = "Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km";
                    calculationHistory.add(calculationResult);
                } else {
                    System.out.println("Invalid unit. Route value will be displayed in default units.");
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km");
                }
            }

            // Get the points between start and end
            List<Point> pointsBetween = cal.getPointsBetween(starts, ends);
            int count = 0;
            if (!pointsBetween.isEmpty()) {
                System.out.println("Points between (" + startXC + ") and (" + endYC + "):");
                System.out.print("(" + starts.getCityName() + ") ---> ");
                for (Point point : pointsBetween) {
                    System.out.print("(" + point.getCityName() + ") ---> ");
                }
                System.out.print("(" + ends.getCityName() + ")");
                System.out.println("\n");
            }

            // Displays each point object in the list
            for (Point point : pointsBetween)
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
        }

        scan.close();

        System.out.println("\nCalculation History:");
        for (String calculation : calculationHistory) {
            System.out.println(calculation);
        }

        System.out.println("\n✨✨✨ Thank you for using Navigator! ✨✨✨\nIf you would like to use the app again, please make a selection from the main menu.");

    }
}
