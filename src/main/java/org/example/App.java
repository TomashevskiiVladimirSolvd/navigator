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

public class App {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main( String[] args ) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
       
    }
}
