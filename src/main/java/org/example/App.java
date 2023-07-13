package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.model.Route;
import org.example.model.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import org.example.model.User;
import org.example.service.implementation.PointService;
import org.example.service.implementation.RouteService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final Logger logger = LogManager.getLogger("APP");

    public static void main( String[] args ) {
        User user = UserRegistration.start(); // returns the current user of the program after registration
        // add other app implementation below here
        // if you want to receive information about the user, use the user object above

    }


}
