package jUnitTesting;

import org.example.ShortestPathCalculator;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.builder.PointBuilder;
import org.example.model.builder.RouteBuilder;
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
        // Testing shortest path from A to D
        long shortestPathAD = calculator.calculateShortestPath(points.get(0), points.get(3));
        assertEquals(35, shortestPathAD);

        // Test shortest path from B to C
        long shortestPathBC = calculator.calculateShortestPath(points.get(1), points.get(2));
        assertEquals(5, shortestPathBC);

        // Test shortest path from C to A
        long shortestPathCA = calculator.calculateShortestPath(points.get(2), points.get(0));
        assertEquals(-1, shortestPathCA);

        // Test when there is no path from start to end
        long shortestPathBD = calculator.calculateShortestPath(points.get(1), points.get(3));
        assertEquals(25, shortestPathBD);
    }

    @Test
    public void testGetPointsBetween() {
        // Test points between A and D
        List<Point> pointsBetweenAD = calculator.getPointsBetween(points.get(0), points.get(3));
        List<Point> expectedPointsBetweenAD = Arrays.asList(points.get(0), points.get(1), points.get(2));
        assertEquals(expectedPointsBetweenAD, pointsBetweenAD);

        // Test points between B and C
        List<Point> pointsBetweenBC = calculator.getPointsBetween(points.get(1), points.get(2));
        List<Point> expectedPointsBetweenBC = Arrays.asList(points.get(1));
        assertEquals(expectedPointsBetweenBC, pointsBetweenBC);

        // Test points between C and A
        List<Point> pointsBetweenCA = calculator.getPointsBetween(points.get(2), points.get(0));
        List<Point> expectedPointsBetweenCA = Arrays.asList(points.get(2), points.get(3));
        assertEquals(expectedPointsBetweenCA, pointsBetweenCA);
    }

}
