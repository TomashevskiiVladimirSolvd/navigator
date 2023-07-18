package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Point;
import org.example.model.Route;

public class ShortestPathCalculator {
    private static final Logger logger = LogManager.getLogger("ShortestPathCalculator");
    private List<Point> points;
    private List<Route> routes;

    public ShortestPathCalculator(List<Point> points, List<Route> routes) {
        this.points = points;
        this.routes = routes;
    }

    public long calculateShortestPath(Point start, Point end) {
        // Find the start and end points by ID
        Point starts = points.stream()
                .filter(p -> p.getId().equals(start.getId()))
                .findFirst()
                .orElse(null);
        Point ends = points.stream()
                .filter(p -> p.getId().equals(end.getId()))
                .findFirst()
                .orElse(null);


        if (starts == null || ends == null) {
            logger.warn("Invalid start or end point ID.");
            return -1;
        }

        Map<Point, Long> distances = new HashMap<>();
        Map<Point, Point> previousPoints = new HashMap<>();
        PriorityQueue<PointEntry> queue = new PriorityQueue<PointEntry>(Comparator.comparingLong(pe -> pe.distance));

        // Initialize distances
        for (Point point : points) {
            if (point.equals(starts)) {
                distances.put(point, 0L);
            } else {
                distances.put(point, Long.MAX_VALUE);
            }
        }

        queue.add(new PointEntry(start, 0L));

        while (!queue.isEmpty()) {
            PointEntry current = queue.poll();
            Point currentPoint = current.point;

            if (currentPoint.equals(end)) {
                return current.distance;
            }

            if (current.distance > distances.get(currentPoint)) {
                continue;
            }

            for (Route route : routes) {
                if (route.getStartPoint().equals(currentPoint)) {
                    Point neighbor = route.getEndPoint();
                    long newDistance = distances.get(currentPoint) + route.getDistance();
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previousPoints.put(neighbor, currentPoint);
                        queue.add(new PointEntry(neighbor, newDistance));
                    }
                }
            }
            logger.debug("The shortest path has been calculated.");
        }

        // If there's no path from start to end
        return -1;
    }

    public List<Point> getPointsBetween(Point start, Point end) {
        List<Point> pointsBetween = new ArrayList<>();

        Point currentPoint = getNextPoint(start);
        while (!currentPoint.equals(end)) {
            pointsBetween.add(currentPoint);
            currentPoint = getNextPoint(currentPoint);
            if (currentPoint == null) {
                // No more points to traverse
                break;
            }
        }
        logger.debug("A list of waypoints has been retrieved.");
        return pointsBetween;
    }

    private Point getNextPoint(Point currentPoint) {
        for (Route route : routes) {
            if (route.getStartPoint().equals(currentPoint)) {
                logger.debug("The next point has been retrieved.");
                return route.getEndPoint();
            }
        }
        logger.debug("There is no next point.");
        return null;
    }

    public List<Route> getRouteHistory(Point start, Point end) {
        List<Route> routeHistory = new ArrayList<>();
        List<Point> pointsBetween = getPointsBetween(start, end);

        for (int i = 0; i < pointsBetween.size() - 1; i++) {
            Point startPoint = pointsBetween.get(i);
            Point endPoint = pointsBetween.get(i + 1);

            for (Route route : routes) {
                if (route.getStartPoint().equals(startPoint) && route.getEndPoint().equals(endPoint)) {
                    routeHistory.add(route);
                    break;
                }
            }
        }
        logger.debug("The route history has been retrieved.");
        return routeHistory;
    }

    public static long kilometersToMiles(long kilometers) {
        return (long) (kilometers * 0.621371);
    }

}


