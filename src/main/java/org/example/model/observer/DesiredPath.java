package org.example.model.observer;

import org.example.model.Point;
import org.example.model.Route;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class DesiredPath {
    private Point currentLocation;
    private Point destination;
    private Route route;
    private List<User> subscribers = new ArrayList<>();

    public void subscribe(User user) {
        subscribers.add(user);
    }

    public void unsubscribe(User user) {
        subscribers.remove(user);
    }

    public void notifyUsers() {
        for (User user : subscribers) {
            user.update();
        }
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }
}
