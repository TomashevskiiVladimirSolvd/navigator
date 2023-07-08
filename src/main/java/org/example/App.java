package org.example;



import java.util.ArrayList;
import java.util.Scanner;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.example.dao.MapperImpl.PointMapperImpl;
import org.example.dao.MapperImpl.RouteMapperImpl;
import org.example.dao.PointDAO;
import org.example.dao.PointDAOImpl;
import org.example.dao.RouteDAO;
import org.example.dao.RouteDAOImpl;
import org.example.model.Route;
import org.example.model.Point;
import java.util.List;


import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.myBatis.MyBatisSessionManager;

public class App {
    private static final Logger logger = Logger.getLogger("GLOBAL");

    public static void main( String[] args ) {

        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);
        List<Point> pt = Stream.generate(randomPointsGenerator::createRandomPoint)
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(pt);

        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 0, 0));
        points.add(new Point(2, 1, 1));
        points.add(new Point(3, 2, 2));
        points.add(new Point(4, 3, 3));

        List<Route> routes = new ArrayList<>();
        routes.add(new Route(points.get(0), points.get(1), 3));
        routes.add(new Route(points.get(1), points.get(2), 4));
        routes.add(new Route(points.get(2), points.get(3), 5));

        //it wont work as navigate.route table no longer exist so the dao layer needs to be fixed the sql create
        // and id column is missing in one of the tables

//        PointDAO pointDAO = new PointDAOImpl();
//        RouteDAO routeDAO = new RouteDAOImpl();
//
//        List<Point> points = pointDAO.getPoints();
//        List<Route> routes = routeDAO.getRoutes();

        // Get start and end coordinates from command line arguments
        Scanner scanner = new Scanner(System.in);

        // Get start and end coordinates from user input
        System.out.print("Enter the x-coordinate of the start point: ");
        double startX = scanner.nextDouble();
        System.out.print("Enter the y-coordinate of the start point: ");
        double startY = scanner.nextDouble();
        System.out.print("Enter the x-coordinate of the end point: ");
        double endX = scanner.nextDouble();
        System.out.print("Enter the y-coordinate of the end point: ");
        double endY = scanner.nextDouble();

        Point start = null;
        Point end = null;

        // Find the start and end points
        for (Point point : points) {
            if (point.getXCoordinate() == startX && point.getYCoordinate() == startY) {
                start = point;
            }
            if (point.getXCoordinate() == endX && point.getYCoordinate() == endY) {
                end = point;
            }
        }

        // Create the calculator instance
        ShortestPathCalculator calculator = new ShortestPathCalculator(points, routes);

        // Calculate the shortest path
        long shortestPath = calculator.calculateShortestPath(start, end);

        if (shortestPath == -1) {
            System.out.println("No path found from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
        } else {
            System.out.println("Shortest path from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") is " + shortestPath);
        }

//        try (SqlSession session = MyBatisSessionManager.getSqlSession()) {
//            PointDAO pointMapper = session.getMapper(PointDAO.class);
//            RouteDAO routeMapper = session.getMapper(RouteDAO.class);
//
//            // Retrieve points and routes from the database
//            List<Point> points = pointMapper.getPoints();
//            List<Route> routes = routeMapper.getRoutes();
//
//            // Find the start and end points
//            for (Point point : points) {
//                if (point.getXCoordinate() == startX && point.getYCoordinate() == startY) {
//                    start = point;
//                }
//                if (point.getXCoordinate() == endX && point.getYCoordinate() == endY) {
//                    end = point;
//                }
//            }
//
//            // Create the calculator instance
//            ShortestPathCalculator calculator = new ShortestPathCalculator(points, routes);
//
//            // Calculate the shortest path
//            long shortestPath = calculator.calculateShortestPath(start, end);
//
//            if (shortestPath == -1) {
//                System.out.println("No path found from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
//            } else {
//                System.out.println("Shortest path from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") is " + shortestPath);
//            }
//        }



    }
}
