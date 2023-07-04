package org.example.dao;

import org.example.model.Point;
import org.example.model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    private Connection connection;

    public RouteDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertRoute(Route route) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO route (startPointId, endPointId, shortestDistance) VALUES (?, ?, ?)");
            statement.setInt(1, route.getStartPoint().getId());
            statement.setInt(2, route.getEndPoint().getId());
            statement.setLong(3, route.getShortestDistance());
            statement.executeUpdate();
            System.out.println("Route inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoute(Route route) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE route SET shortestDistance = ? WHERE startPointId = ? AND endPointId = ?");
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
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM route WHERE startPointId = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int startPointId = resultSet.getInt("startPointId");
                int endPointId = resultSet.getInt("endPointId");
                long shortestDistance = resultSet.getLong("shortestDistance");

                PointDAO pointDAO = new PointDAOImpl();

                Point startPoint = pointDAO.getPoint(startPointId);
                Point endPoint = pointDAO.getPoint(endPointId);

                return new Route(startPoint, endPoint, shortestDistance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM route");
            PointDAO pointDAO = new PointDAOImpl(); // Assuming you have implemented the PointDAO
            while (resultSet.next()) {
                int startPointId = resultSet.getInt("startPointId");
                int endPointId = resultSet.getInt("endPointId");
                long shortestDistance = resultSet.getLong("shortestDistance");

                Point startPoint = pointDAO.getPoint(startPointId);
                Point endPoint = pointDAO.getPoint(endPointId);

                routes.add(new Route(startPoint, endPoint, shortestDistance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }
}

