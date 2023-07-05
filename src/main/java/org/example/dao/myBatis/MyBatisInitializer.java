package org.example.dao.myBatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;


//This class is responsible for initializing the SqlSessionFactory object adhering to the SRP single responsibility
public class MyBatisInitializer {
  private static final Logger logger = Logger.getLogger("GLOBAL");
  private static SqlSessionFactory sqlSessionFactory;

  public static void initialize() {
    try {
      String mybatisConfigFile = "src/main/resources/mybatis-config.xml";
      Reader reader = Resources.getResourceAsReader(mybatisConfigFile);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    } catch (IOException e) {
      logger.error("Failed to find MyBatis configuration file");
    }
  }

  public static SqlSessionFactory getSqlSessionFactory() {
    return sqlSessionFactory;
  }
}


