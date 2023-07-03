package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ibatis.session.SqlSession;
import org.example.configurations.ConnectionPool;
import org.example.myBatis.MyBatisInitializer;
import org.example.myBatis.MyBatisSessionManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnectionFromPool();

        try (Statement statement = connection.createStatement()) {
            // Execute a sample query to test the connection
            String query = "SELECT 1";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int result = resultSet.getInt(1);
                System.out.println("Database connection successful. Result: " + result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnectionToPool(connection);
        }

        // Initialize MyBatis
        MyBatisInitializer.initialize();

        // Get a SqlSession
        SqlSession sqlSession = MyBatisSessionManager.getSqlSession();

        try {
            // Execute a sample query to test the connection
            int count = sqlSession.selectOne("your.mapper.statement");

            System.out.println("Database connection successful. Result: " + count);
        } finally {
            // Close the SqlSession
            sqlSession.close();
        }
    }
}
