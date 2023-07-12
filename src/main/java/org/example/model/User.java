package org.example.model;


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
    private DesiredPath desiredPath;

    public User(){

    }
    public User(String name, String surname, String email) {
        this.name=name;
        this.surname=surname;
        this.email=email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
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
