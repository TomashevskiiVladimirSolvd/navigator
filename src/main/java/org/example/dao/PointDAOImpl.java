package org.example.dao;

import org.example.configurations.ConnectionPool;
import org.example.model.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO point (id, xCoordinate, yCoordinate) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, point.getId());
            statement.setDouble(2, point.getXCoordinate());
            statement.setDouble(3, point.getYCoordinate());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                point.setId(id);
                System.out.println("Point inserted successfully. Generated ID: " + id);
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
    public void deletePoint(Point point) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM point WHERE id = ?");
            statement.setInt(1, point.getId());
            statement.executeUpdate();
            System.out.println("Point deleted successfully.");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }

    @Override
    public Point getPoint(int id) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        Point point = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM point WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double xCoordinate = resultSet.getDouble("xCoordinate");
                double yCoordinate = resultSet.getDouble("yCoordinate");
                point = new Point(id, xCoordinate, yCoordinate);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
        return point;
    }

    @Override
    public List<Point> getPoints() {
        Connection connection = CONNECTIONPOOL.releaseConnectionToPool();
        List<Point> points = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM point");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double xCoordinate = resultSet.getDouble("xCoordinate");
                double yCoordinate = resultSet.getDouble("yCoordinate");
                points.add(new Point(id, xCoordinate, yCoordinate));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
        return points;
    }
}