package org.example.configuration;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MyBatisSession {
  public static SqlSession getSqlSession() {
    MyBatisInitializer.initialize(); // Call the initialize() method before accessing the SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = MyBatisInitializer.getSqlSessionFactory();
    return sqlSessionFactory.openSession();
  }
}