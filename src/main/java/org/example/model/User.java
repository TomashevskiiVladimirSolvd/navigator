package org.example.model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.observer.DesiredPath;

import java.util.Objects;

public class User {
    private static final Logger logger = LogManager.getLogger("User");
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private DesiredPath desiredPath;

    public User() {

    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        logger.debug("The user id has been retrieved.");
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        logger.debug("The user id has been set.");
    }

    public String getName() {
        logger.debug("The user name has been retrieved.");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        logger.debug("The user name has been set.");
    }

    public String getSurname() {
        logger.debug("The user surname has been retrieved.");
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        logger.debug("The user surname has been set.");
    }

    public String getEmail() {
        logger.debug("The user email has been retrieved.");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        logger.debug("The user email has been set.");
    }

    public String getPassword() {
        logger.debug("The user password has been retrieved.");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        logger.debug("The user password has been set.");
    }

    public DesiredPath getDesiredPath() {
        logger.debug("The desired path has been retrieved.");
        return desiredPath;
    }

    public void setDesiredPath(DesiredPath desiredPath) {
        this.desiredPath = desiredPath;
        logger.debug("The desired path has been set.");
    }

    public void update() {
        logger.info(String.format("Your destination is %s m away", getDesiredPath().getRoute().getDistance()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(desiredPath, user.desiredPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, desiredPath);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", desiredPath=" + desiredPath +
                '}';

    }


}