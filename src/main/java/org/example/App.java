package org.example;


<<<<<<< HEAD
=======
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
>>>>>>> origin/main
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ibatis.session.SqlSession;
import org.example.configurations.ConnectionPool;
import org.example.myBatis.MyBatisInitializer;
import org.example.myBatis.MyBatisSessionManager;
import org.example.model.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main( String[] args ) {
<<<<<<< HEAD

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


=======
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);
        List<Point> points = Stream.generate(randomPointsGenerator::createRandomPoint)
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(points);
>>>>>>> origin/main
    }
}
