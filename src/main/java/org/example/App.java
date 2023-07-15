package org.example;


import java.util.ArrayList;
import java.util.Scanner;


import org.apache.commons.lang3.math.NumberUtils;
import org.example.model.Route;
import org.example.model.Point;

import org.example.model.builder.RouteBuilder;
import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.List;

public class App {
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

        System.out.println("\nLoading cities...");

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

            Point starts = pointService.getPoint(startXC);
            Point ends = pointService.getPoint(endYC);

            // Check if start or end points were not found
            if (starts == null || ends == null) {
                System.out.println("Invalid start or end point ID.");
                continue;
            }

            // Create the calculator instance
            ShortestPathCalculator cal = new ShortestPathCalculator(allPoints, allRoutes);

            // Calculate the shortest path
            /*long shortestP = cal.calculateShortestPath(starts, ends);
            boolean unitMiles;

            if (shortestP == -1) {
                System.out.println("No path found from (" + startXC + ") to (" + endYC + ")");
            } else {
                System.out.print("Enter the desired unit (miles or km): ");
                String unit = scan.next();

                if (unit.equalsIgnoreCase("miles")) {
                    shortestP = ShortestPathCalculator.kilometersToMiles(shortestP);
                    System.out.println("\nShortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " miles");
                    String calculationResult = "Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " miles";
                    unitMiles = true;
                    calculationHistory.add(calculationResult);
                } else if (unit.equalsIgnoreCase("km")) {
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km");
                    String calculationResult = "Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km";
                    unitMiles = false;
                    calculationHistory.add(calculationResult);
                } else {
                    System.out.println("Invalid unit. Route value will be displayed in default units.");
                    System.out.println("Shortest path from (" + startXC + ") to (" + endYC + ") is " + shortestP + " km");
                    unitMiles = false;
                }
            }*/

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

                Route route = routeBuilder.setStartPoint(pointService.getPoint(startXC))
                        .setEndPoint(pointService.getPoint(endYC))
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

            /*// Get the points between start and end
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
            }*/
        }

        scan.close();

        System.out.println("\nCalculation History:");
        for (String calculation : calculationHistory) {
            System.out.println(calculation);
        }

        System.out.println("\n✨✨✨ Thank you for using Navigator! ✨✨✨\nIf you would like to use the app again, please make a selection from the main menu.");

    }
}
