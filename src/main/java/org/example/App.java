package org.example;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    public static void main( String[] args ) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");

        System.out.println( "Hello World!" );

       
    }
}
