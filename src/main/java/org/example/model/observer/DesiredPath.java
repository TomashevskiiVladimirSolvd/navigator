package org.example.model.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class DesiredPath {
    private static final Logger logger = LogManager.getLogger("DesiredPath");
    private Point currentLocation;
    private Point destination;
    private Route route;
    private List<User> subscribers;

    public void subscribe(User user) {
        subscribers.add(user);
        logger.debug("A user has been added to the list of subscribers.");
    }

    public void unsubscribe(User user) {
        subscribers.remove(user);
        logger.debug("A user has been removed from the list of subscribers.");
    }

    public void notifyUsers() {
        for (User user : subscribers) {
            user.update();
        }
        logger.debug("Each user from the list of subscribers has been updated.");
    }

    public Point getCurrentLocation() {
        logger.debug("The current location has been retrieved.");
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
        logger.debug("The current location has been set.");
    }

    public Point getDestination() {
        logger.debug("A destination has been retrieved.");
        return destination;
    }

    public void setDestination(Point destination) {
        logger.debug("A destination has been set.");
        this.destination = destination;
    }

    public Route getRoute() {
        logger.debug("A route has been retrieved.");
        return route;
    }

    public void setRoute(Route route) {
        logger.debug("A route has been set.");
        this.route = route;
    }

    public List<User> getSubscribers() {
        logger.debug("A list of routes has been retrieved.");
        return subscribers;
    }
}
