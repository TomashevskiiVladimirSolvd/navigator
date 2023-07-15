package jUnitTesting;

import org.example.UserRegistration;
import org.example.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRegistrationTest {
    public UserRegistration userRegistration;

    @Before
    public void setUp() {
        userRegistration = new UserRegistration();
    }

    @Test
    public void testStart() {

        String simulatedUserInput = "1\nMary\nKonn\nmarykonn@gmail.com\npassword\npassword\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);
        userRegistration.start();

        User newUser=userRegistration.userService.getUser("marykonn@gmail.com");
        assertNotNull(newUser);
        assertEquals("Mary", newUser.getName());
        assertEquals("Konn", newUser.getSurname());
        assertEquals("marykonn@gmail.com", newUser.getEmail());
        assertEquals("password", newUser.getPassword());
    }

    @Test
    public void testStartExistingUser() {
        String simulatedUserInput = "2\nangelina@gmail.com\npassword\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        User existingUser=userRegistration.userService.getUser("angelina@gmail.com");
        System.out.println("existing user info: "+existingUser);
        userRegistration.start();
        User returnedUser=userRegistration.getCurrentUser();

        assertNotNull(returnedUser);
        assertEquals(existingUser.getName(), returnedUser.getName());
        assertEquals(existingUser.getSurname(), returnedUser.getSurname());
        assertEquals(existingUser.getEmail(), returnedUser.getEmail());
        assertEquals(existingUser.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void testCapitalize() {
        String input = "mary";
        String capitalizedInput = userRegistration.capitalize(input);
        assertEquals("Mary", capitalizedInput);
    }
}
