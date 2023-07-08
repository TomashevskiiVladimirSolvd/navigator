package org.example.configuration;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class MyBatisInitializer {
  private static final Logger logger = Logger.getLogger(MyBatisInitializer.class);
  private static SqlSessionFactory sqlSessionFactory;
  private static final String configFile = "src/main/resources/config.properties";
  private static final String myBatisFile = "mybatis-config.xml";

  public static void initialize() {
    Properties properties = new Properties();

    try (FileInputStream fis = new FileInputStream(configFile)) {
      properties.load(fis);
      Reader reader = Resources.getResourceAsReader(myBatisFile);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
    } catch (IOException e) {
      logger.error("An error occurred while initializing MyBatis:", e);
    }
  }

  public static SqlSessionFactory getSqlSessionFactory() {
    return sqlSessionFactory;
  }
}