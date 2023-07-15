package org.example;
import java.util.Scanner;

import org.example.model.User;
import org.example.model.builder.UserBuilder;
import org.example.service.implementation.UserService;


public class UserRegistration {
  public final static UserService userService = new UserService();
  private final static int maxAttempts = 3;
  private static User user;

  public static void start() {
    Scanner scanner = new Scanner(System.in);

    boolean running = true;
    while (running) {
      System.out.println("\n✦✦✦ MAIN MENU ✦✦✦");
      System.out.println("[ 1 ] Sign up");
      System.out.println("[ 2 ] Log in");
      System.out.println("[ 3 ] Exit");
      System.out.print("Enter a selection: ");
      String input = scanner.nextLine();

      String firstName;
      String lastName;
      String email;
      String pass1;
      String pass2;
      int count = 0;
      boolean match = false;

      switch (input) {
        case "1": // creates new user and records into database
          System.out.println("\n✦✦✦ CREATE AN ACCOUNT ✦✦✦");
          System.out.print("Enter your first name: ");
          firstName = containsWhitespace(capitalize(scanner.nextLine()));
          System.out.print("Enter your last name: ");
          lastName = containsWhitespace(capitalize(scanner.nextLine()));
          System.out.print("Enter your email: ");
          email = scanner.nextLine();

          user = userService.getUser(email);
          if (user != null) {
            System.out.println("\n*** This email is already associated with another account. Please log in instead. ***");
            break;
          }

          System.out.println("Please create a strong password to keep your account secured.");
          while (!match) {
            System.out.print("Enter your password: ");
            pass1 = containsWhitespace(scanner.nextLine());
            System.out.print("Enter your password again: ");
            pass2 = containsWhitespace(scanner.nextLine());
            if (!pass1.equals(pass2)) {
              System.out.println(">>> The passwords do not match. Please enter your password again. <<<");
            } else {
              System.out.println("Please wait while we create your account...");
              User newUser = new UserBuilder().setName(firstName).setSurname(lastName).setEmail(email).setPassword(pass1).getUser();
              userService.create(newUser);
              System.out.println("\nWelcome, " + firstName + "!");
              System.out.println("Your account has been created. Thank you for using Navigator!\n");
              running = false;
              match = true;
            }
          }
          break;
        case "2": // retrieves returning user from database
          System.out.println("\n✦✦✦ LOG IN ✦✦✦");
          boolean login = false;
          while (!login) {
            System.out.print("Enter your email: ");
            email = containsWhitespace(scanner.nextLine());
            user = userService.getUser(email);
            if (user != null) {
              while (!match) {
                System.out.print("Enter your password: ");
                pass1 = containsWhitespace(scanner.nextLine());
                pass2 = user.getPassword();
                if (pass1.equals(pass2)) {
                  System.out.println("\nWelcome back, " + user.getName() + "!\n");
                  match = true;
                  login = true;
                  running = false;
                } else {
                  System.out.println(">>> The password provided was incorrect. <<<");
                  count++;
                  if (count == maxAttempts) {
                    System.out.println(">>> You have attempted to access your account too many times, returning to the main menu. <<<");
                    login = true;
                    break;
                  }
                }
              }
            } else {
              System.out.println(">>> This email is not associated with an account. <<<");
              count++;
              if (count == maxAttempts) {
                System.out.println(">>> You have attempted to access your account too many times, returning to the main menu. <<<");
                login = true;
              }
            }
          }
          break;
        case "3":
          System.out.println("\n✨✨✨ Thank you for using Navigator, goodbye! ✨✨✨");
          System.exit(0);
          break;
        default:
          System.out.println("\n*** Sorry, that is not a valid option. Please sign up or log in if you already have an account. ***\n");
          break;
      }
    }
  }

  public static User getCurrentUser() {
    return user;
  }

  public static String capitalize(String str) {
    return str.substring(0, 1).toUpperCase().concat(str.substring(1).toLowerCase());
  }

  public static String containsWhitespace(String str) {
    Scanner scanner = new Scanner(System.in);
    while (str.contains(" ")) {
      System.out.println("Cannot contain whitespaces.");
      System.out.print("Try again: ");
      str = scanner.nextLine();
    }
    return str;
  }
}
