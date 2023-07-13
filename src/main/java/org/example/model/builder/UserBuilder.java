package org.example.model.builder;

import org.example.model.User;

public class UserBuilder {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public UserBuilder() {

    }

    public UserBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public User getUser() {
        return new User(name, surname, email, password);
    }
}
