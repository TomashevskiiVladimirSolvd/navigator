package org.example.dao;

import org.example.configurations.ConnectionPool;
import org.example.model.Point;
import org.example.model.ShortestPath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShortestPathDAOImpl implements ShortestPathDAO {
    private static final ConnectionPool CONNECTIONPOOL = ConnectionPool.getInstance();

    public ShortestPathDAOImpl() {
    }

    @Override
    public void insertShortestPath(ShortestPath shortestPath) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try (
             PreparedStatement statement = connection.prepareStatement("INSERT INTO shortest_paths (id, previous_point, distance) VALUES (?, ?, ?)")) {
            statement.setInt(1, shortestPath.getId());
            statement.setObject(2, shortestPath.getPreviousPoint());
            statement.setLong(3, shortestPath.getDistance());
            statement.executeUpdate();
            System.out.println("Shortest path inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }

    @Override
    public void updateShortestPath(ShortestPath shortestPath) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try (
             PreparedStatement statement = connection.prepareStatement("UPDATE shortest_paths SET previous_point = ?, distance = ? WHERE id = ?")) {
            statement.setObject(1, shortestPath.getPreviousPoint());
            statement.setLong(2, shortestPath.getDistance());
            statement.setInt(3, shortestPath.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Shortest path updated successfully.");
            } else {
                System.out.println("Shortest path not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
    }

    @Override
    public ShortestPath getShortestPath(int id) {
        Connection connection = CONNECTIONPOOL.getConnectionFromPool();
        try (
             PreparedStatement statement = connection.prepareStatement("SELECT sp.id AS shortestPathId, sp.previous_point AS previousPoint, sp.distance AS distance FROM shortest_paths sp WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    PointDAO pointDAO = new PointDAOImpl();

                    int shortestPathId = resultSet.getInt("shortestPathId");
                    Point previousPoint = pointDAO.getPoint(resultSet.getInt("previousPoint"));
                    long distance = resultSet.getLong("distance");

                    ShortestPath shortestPath = new ShortestPath(shortestPathId, previousPoint, distance);
                    return shortestPath;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTIONPOOL.releaseConnectionToPool(connection);
        }
        return null;
    }
}

