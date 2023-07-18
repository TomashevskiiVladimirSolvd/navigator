package jUnitTesting;

import org.example.ShortestPathCalculator;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.builder.PointBuilder;
import org.example.model.builder.RouteBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShortestPathCalculatorTest {
    private List<Point> points;
    private List<Route> routes;
    private ShortestPathCalculator calculator;

    @Before
    public void setUp() {
        // Creating some test data
        Point pointA = new PointBuilder().setCityName("Chicago").getPoint();
        Point pointB = new PointBuilder().setCityName("Miami").getPoint();
        Point pointC = new PointBuilder().setCityName("Los Angeles").getPoint();
        Point pointD = new PointBuilder().setCityName("New York").getPoint();

        points = Arrays.asList(pointA, pointB, pointC, pointD);

        Route routeAB = new RouteBuilder().setStartPoint(pointA).setEndPoint(pointB).setDistance(10).getRoute();
        Route routeAC = new RouteBuilder().setStartPoint(pointA).setEndPoint(pointC).setDistance(15).getRoute();
        Route routeBC = new RouteBuilder().setStartPoint(pointB).setEndPoint(pointC).setDistance(5).getRoute();
        Route routeCD = new RouteBuilder().setStartPoint(pointC).setEndPoint(pointD).setDistance(20).getRoute();

        routes = Arrays.asList(routeAB, routeAC, routeBC, routeCD);

        calculator = new ShortestPathCalculator(points, routes);
    }

    @Test
    public void testCalculateShortestPath() {
        ShortestPathCalculator calculator = new ShortestPathCalculator(points, routes);
        Point start = points.get(0);
        Point end = points.get(3);

        long shortestPath = calculator.calculateShortestPath(start, end);

        Assert.assertEquals(25, shortestPath);
    }

    @Test
    public void testGetPointsBetween() {
        ShortestPathCalculator calculator = new ShortestPathCalculator(points, routes);
        Point start = points.get(0);
        Point end = points.get(3);

        List<Point> pointsBetween = calculator.getPointsBetween(start, end);

        assertEquals(3, pointsBetween.size());
        assertEquals(points.get(1), pointsBetween.get(0));
        assertEquals(points.get(2), pointsBetween.get(1));
        assertEquals(points.get(3),pointsBetween.get(2));
    }
    @Test
    public void testGetRouteHistory() {
        Point start = points.get(0);
        Point end = points.get(3);

        List<Route> routeHistory = calculator.getRouteHistory(start, end);

        assertEquals(2, routeHistory.size());
        assertEquals(routes.get(0), routeHistory.get(0));
        assertEquals(routes.get(2), routeHistory.get(1));
    }

    @Test
    public void testKilometersToMiles() {
        long kilometers = 10;
        long miles = ShortestPathCalculator.kilometersToMiles(kilometers);

        assertEquals(6, miles);
    }

}
