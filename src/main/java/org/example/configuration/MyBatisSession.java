package org.example.configuration;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

//This class is responsible for retrieving the SqlSession object adhering to the SRP single responsibility
public class MyBatisSessionManager {
  public static SqlSession getSqlSession() {
    MyBatisInitializer.initialize(); // Call the initialize() method before accessing the SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = MyBatisInitializer.getSqlSessionFactory();
    return sqlSessionFactory.openSession();
  }

}
