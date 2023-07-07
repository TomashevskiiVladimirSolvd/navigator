package org.example;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);
        List<Point> points = Stream.generate(randomPointsGenerator::createRandomPoint)
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(points);
    }
}
