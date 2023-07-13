package jUnitTesting;

import org.example.UserRegistration;
import org.example.model.User;
import org.example.model.builder.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.example.UserRegistration.userService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRegistrationTest {
    private UserRegistration userRegistration;

    @Before
    public void setUp() {
        userRegistration = new UserRegistration();
    }

    @Test
    public void testStart() {
        String simulatedUserInput = "1\nMary\nKonn\nmarykonn@gmail.com\npassword\npassword\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        User user = userRegistration.start();

        assertNotNull(user);
        assertEquals("Mary", user.getName());
        assertEquals("Konn", user.getSurname());
        assertEquals("marykonn@gmail.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testStartExistingUser() {
        String simulatedUserInput = "2\nmarykonn@gmail.com\npassword\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        User user = new UserBuilder().setName("Mary").setSurname("Konn").setEmail("marykonn@gmail.com").setPassword("password").getUser();
        userService.create(user);

        User returnedUser = userRegistration.start();

        assertNotNull(returnedUser);
        assertEquals(user.getName(), returnedUser.getName());
        assertEquals(user.getSurname(), returnedUser.getSurname());
        assertEquals(user.getEmail(), returnedUser.getEmail());
        assertEquals(user.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void testCapitalize() {
        String input = "mary";
        String capitalizedInput = userRegistration.capitalize(input);
        assertEquals("Mary", capitalizedInput);
    }
}
