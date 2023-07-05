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
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO route (startPointId, endPointId, shortestDistance) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, route.getStartPoint().getId());
            statement.setInt(2, route.getEndPoint().getId());
            statement.setLong(3, route.getShortestDistance());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                route.setId(id);
                System.out.println("Route inserted successfully. Generated ID: " + id);
            }

            statement.close();
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }

    @Override
    public void updateRoute(Route route) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
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
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }

    @Override
    public Route getRoute(int id) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        Route route = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM route WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int startPointId = resultSet.getInt("startPointId");
                int endPointId = resultSet.getInt("endPointId");
                long shortestDistance = resultSet.getLong("shortestDistance");

                PointDAO pointDAO = new PointDAOImpl();
                Point startPoint = pointDAO.getPoint(startPointId);
                Point endPoint = pointDAO.getPoint(endPointId);

                route = new Route(startPoint, endPoint, shortestDistance);
                route.setId(id);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
        return route;
    }

    @Override
    public List<Route> getRoutes() {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        List<Route> routes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM route");
            PointDAO pointDAO = new PointDAOImpl();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int startPointId = resultSet.getInt("startPointId");
                int endPointId = resultSet.getInt("endPointId");
                long shortestDistance = resultSet.getLong("shortestDistance");

                Point startPoint = pointDAO.getPoint(startPointId);
                Point endPoint = pointDAO.getPoint(endPointId);

                Route route = new Route(startPoint, endPoint, shortestDistance);
                route.setId(id);
                routes.add(route);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
        return routes;
    }
}