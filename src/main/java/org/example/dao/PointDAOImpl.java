package org.example.dao;

import org.example.configurations.ConnectionPool;
import org.example.model.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PointDAOImpl implements PointDAO {
    private static final ConnectionPool CONNECTIONPOOL = ConnectionPool.getInstance();

    public PointDAOImpl() {
    }

    @Override
    public void insertPoint(Point point) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO point (x_coordinate, y_coordinate) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, point.getXCoordinate());
            statement.setDouble(2, point.getYCoordinate());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    point.setId(id);
                    System.out.println("Point inserted successfully. Generated ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }


    @Override
    public void deletePoint(int id) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM points WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Point with ID " + id + " deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }


    @Override
    public Point getPoint(int id) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        Point point = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT x_coordinate AS xCoordinate, y_coordinate AS yCoordinate FROM points WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double xCoordinate = resultSet.getDouble("xCoordinate");
                    double yCoordinate = resultSet.getDouble("yCoordinate");
                    point = new Point(id, xCoordinate, yCoordinate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
        return point;
    }

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        try (Connection connection = CONNECTIONPOOL.getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement("SELECT p.id AS pointId, p.x_coordinate AS xCoordinate, p.y_coordinate AS yCoordinate FROM points p");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("pointId");
                double xCoordinate = resultSet.getDouble("xCoordinate");
                double yCoordinate = resultSet.getDouble("yCoordinate");
                points.add(new Point(id, xCoordinate, yCoordinate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }


}