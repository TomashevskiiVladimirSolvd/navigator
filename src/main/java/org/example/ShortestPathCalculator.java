package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.example.model.Point;
import org.example.model.Route;

public class ShortestPathCalculator {
  private List<Point> points;
  private List<Route> routes;

  public ShortestPathCalculator(List<Point> points, List<Route> routes) {
    this.points = points;
    this.routes = routes;
  }

  public long calculateShortestPath(Point start, Point end) {
    Map<Point, Long> distances = new HashMap<>();
    Map<Point, Point> previousPoints = new HashMap<>();
    PriorityQueue<PointEntry> queue = new PriorityQueue<PointEntry>(Comparator.comparingLong(pe -> pe.distance));

    // Initialize distances
    for (Point point : points) {
      if (point.equals(start)) {
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
    }

    // If there's no path from start to end
    return -1;



  }

}
