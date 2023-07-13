package org.example.model;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Observer.DesiredPath;

import java.util.Objects;

public class User {
    private static final Logger logger = LogManager.getLogger("CLASS");
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private DesiredPath desiredPath;

    public User(){

    }
    public User(String name, String surname, String email, String password) {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public DesiredPath getDesiredPath() {
        return desiredPath;
    }

    public void setDesiredPath(DesiredPath desiredPath) {
        this.desiredPath = desiredPath;
    }

    public void update() {
        logger.info(String.format("Your destination is %s m away", getDesiredPath().getRoute().getDistance()));
    }
}