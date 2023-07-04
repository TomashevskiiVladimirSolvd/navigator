package org.example.configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
  private static final ConnectionPool INSTANCE = new ConnectionPool();
  private List<Connection> connectionPool;
  private Properties configProperties;

  private ConnectionPool() {
    configProperties = Config.getInstance().getProperty();

    try {
      Class.forName(configProperties.getProperty("db.driver"));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    connectionPool = new ArrayList<>(Integer.parseInt(configProperties.getProperty("db.poolsize")));

    for (int i = 0; i < Integer.parseInt(configProperties.getProperty("db.poolsize")); i++) {
      connectionPool.add(createConnection());
    }
  }

  private Connection createConnection() {
    Connection connection;
    try {
      connection = DriverManager.getConnection(
          configProperties.getProperty("db.url"),
          configProperties.getProperty("db.username"),
          configProperties.getProperty("db.password")
      );
    } catch (SQLException e) {
      throw new RuntimeException("Unable to create a connection.", e);
    }
    return connection;
  }

  public Connection getConnectionFromPool() {
    Connection connection = null;
    if (connectionPool.isEmpty()) {
      try {
        synchronized (connectionPool) {
          connectionPool.wait();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException("Unable to connect.", e);
      }
    } else {
      connection = connectionPool.remove(connectionPool.size() - 1);
    }
    return connection;
  }

  public void releaseConnectionToPool(Connection connection) {
    connectionPool.add(connection);
    synchronized (connectionPool) {
      connectionPool.notify();
    }
  }

  public static ConnectionPool getInstance() {
    return INSTANCE;
  }

}
