package org.example.configuration;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class MyBatisInitializer {
  private static SqlSessionFactory sqlSessionFactory;
  private static final String configFile = "src/main/resources/config.properties";
  private static final String myBatisFile = "mybatis-config.xml";

  static {
    initialize();
  }

  public static void initialize() {


    Properties properties = new Properties();
    try (FileInputStream fis = new FileInputStream(configFile)) {
      properties.load(fis);
      Reader reader = Resources.getResourceAsReader(myBatisFile);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);

    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public static SqlSessionFactory getSqlSessionFactory() {
    return sqlSessionFactory;
  }
}