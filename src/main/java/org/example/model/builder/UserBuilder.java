package org.example.model.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.User;

public class UserBuilder {
  private static final Logger logger = LogManager.getLogger("UserBuilder");

  private Integer id;
  private String name;
  private String surname;
  private String email;
  private String password;


  public UserBuilder() {

  }

  public UserBuilder setId(Integer id) {
    this.id = id;
    logger.debug("An id has been set for the user.");
    return this;
  }

  public UserBuilder setName(String name) {
    this.name = name;
    logger.debug("A name has been set for the user.");
    return this;
  }

  public UserBuilder setSurname(String surname) {
    this.surname = surname;
    logger.debug("A surname has been set for the user.");
    return this;
  }

  public UserBuilder setEmail(String email) {
    this.email = email;
    logger.debug("An email has been set for the user.");
    return this;
  }

  public UserBuilder setPassword(String password) {
    this.password = password;
    logger.debug("A password has been set for the user.");
    return this;
  }

  public User getUser() {
    return new User(name, surname, email, password);
  }

}
