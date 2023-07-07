package org.example.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//This class should follow SOLID principles, the Single Responsibility Principle (SRP),
// by having a single responsibility of reading and providing configuration values.
public class Config {
  private static Config instance;
  private Properties properties;
  private static final String CONFIG_PROPERTIES = "config.properties";

  private Config() {
    properties = loadProperties(CONFIG_PROPERTIES);
  }

  public static Config getInstance() {
    if (instance == null) {
      synchronized (Config.class) {
        if (instance == null) {
          instance = new Config();
        }
      }
    }
    return instance;
  }

  public Properties getProperty() {
    return properties;
  }

  private static Properties loadProperties(String propertiesFile) {
    Properties properties = new Properties();
    try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(propertiesFile)) {
      if (inputStream != null) {
        properties.load(inputStream);
      } else {
        throw new RuntimeException("Unable to load properties file: " + propertiesFile);
      }
    } catch (IOException e) {
      throw new RuntimeException("Unable to load properties file: " + propertiesFile, e);
    }
    return properties;
  }

}
