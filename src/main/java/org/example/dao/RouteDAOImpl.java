package org.example.dao;

import org.example.configurations.ConnectionPool;
import org.example.model.Point;
import org.example.model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RouteDAOImpl implements RouteDAO {
    private static final ConnectionPool CONNECTIONPOOL = ConnectionPool.getInstance();

    public RouteDAOImpl() {
    }
    @Override
    public void insertRoute(Route route) {
        try (Connection connection = CONNECTIONPOOL.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO routes (start_point, end_point, distance) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, route.getStartPoint().getId());
            statement.setInt(2, route.getEndPoint().getId());
            statement.setLong(3, route.getShortestDistance());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    route.setId(id);
                    System.out.println("Route inserted successfully. Generated ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoute(Route route) {
        try (Connection connection = CONNECTIONPOOL.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("UPDATE routes SET distance = ? WHERE start_point = ? AND end_point = ?")) {
            statement.setLong(1, route.getShortestDistance());
            statement.setInt(2, route.getStartPoint().getId());
            statement.setInt(3, route.getEndPoint().getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Route updated successfully.");
            } else {
                System.out.println("Route not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Route getRoute(int id) {
        try (Connection connection = CONNECTIONPOOL.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("SELECT r.start_point AS startPointId, r.end_point AS endPointId, r.distance AS shortestDistance FROM routes r WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int startPointId = resultSet.getInt("startPointId");
                    int endPointId = resultSet.getInt("endPointId");
                    long shortestDistance = resultSet.getLong("shortestDistance");

                    PointDAO pointDAO = new PointDAOImpl();
                    Point startPoint = pointDAO.getPoint(startPointId);
                    Point endPoint = pointDAO.getPoint(endPointId);

                    Route route = new Route(startPoint, endPoint, shortestDistance);
                    route.setId(id);
                    return route;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = CONNECTIONPOOL.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("SELECT r.id AS routesId, r.start_point AS startPointId, r.end_point AS endPointId, r.distance AS shortestDistance FROM routes r");
             ResultSet resultSet = statement.executeQuery()) {
            PointDAO pointDAO = new PointDAOImpl();
            while (resultSet.next()) {
                int id = resultSet.getInt("routesId");
                int startPointId = resultSet.getInt("startPointId");
                int endPointId = resultSet.getInt("endPointId");
                long shortestDistance = resultSet.getLong("shortestDistance");

                Point startPoint = pointDAO.getPoint(startPointId);
                Point endPoint = pointDAO.getPoint(endPointId);

                Route route = new Route(startPoint, endPoint, shortestDistance);
                route.setId(id);
                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }

}