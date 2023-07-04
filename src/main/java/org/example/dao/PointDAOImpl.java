package org.example.dao;

import org.example.model.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PointDAOImpl implements PointDAO {
    private Connection connection;

    public PointDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPoint(Point point) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO point (id, xCoordinate, yCoordinate) VALUES (?, ?, ?)");
            statement.setInt(1, point.getId());
            statement.setDouble(2, point.getXCoordinate());
            statement.setDouble(3, point.getYCoordinate());
            statement.executeUpdate();
            System.out.println("Point inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePoint(Point point) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM point WHERE id = ?");
            statement.setInt(1, point.getId());
            statement.executeUpdate();
            System.out.println("Point deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Point getPoint(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM point WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double xCoordinate = resultSet.getDouble("xCoordinate");
                double yCoordinate = resultSet.getDouble("yCoordinate");
                return new Point(id, xCoordinate, yCoordinate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Point> getPoints() {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }
}
