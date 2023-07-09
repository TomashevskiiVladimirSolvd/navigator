package org.example;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.example.model.Observer.DesiredPath;
import org.example.model.Point;
import org.example.model.Route;
import org.example.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main( String[] args ) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        RandomPointsGenerator randomPointsGenerator = new RandomPointsGenerator(0,20,0,20, 5);
        List<Point> points = Stream.generate(randomPointsGenerator::createRandomPoint)
                .limit(randomPointsGenerator.getNumPoints())
                .collect(Collectors.toList());
        System.out.println(points);

        //Observer logic
        User ourMentor = new User(null, "Andrei", "Trukhanovich", "atrukhanovich@solvd.com");
        DesiredPath routing = new DesiredPath();
        routing.subscribe(ourMentor);
        ourMentor.setDesiredPath(routing);
        // calculate the optimal route
        Route route = new Route();
        routing.setRoute(route);
        routing.notifyUsers();
    }
}
