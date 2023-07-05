package org.example.myBatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


//This class is responsible for initializing the SqlSessionFactory object adhering to the SRP single responsibility
public class MyBatisInitializer {
  private static SqlSessionFactory sqlSessionFactory;

  static {
    initialize();
  }

  public static void initialize() {
    try {
      String mybatisConfigFile = "src/main/resources/mybatis-config.xml";
      Reader reader = Resources.getResourceAsReader(mybatisConfigFile);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    } catch (IOException e) {
      System.out.println("Failed to find MyBatis configuration file");
    }
  }

  public static SqlSessionFactory getSqlSessionFactory() {
    return sqlSessionFactory;
  }
}


