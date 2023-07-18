package jUnitTesting;

import org.example.UserRegistration;
import org.example.model.User;
import org.example.service.implementation.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRegistrationTest {
    public UserRegistration userRegistration;

    @Before
    public void setUp() {
        userRegistration = new UserRegistration();
    }

    @Test
    public void testStart() throws Exception {

        String simulatedUserInput = "1\nMary\nKonn\nmarykonn@gmail.com\npassword\npassword\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        // Accessing the private userService field
        Field userServiceField = UserRegistration.class.getDeclaredField("userService");
        userServiceField.setAccessible(true);
        UserService userService = (UserService) userServiceField.get(userRegistration);

        userRegistration.start();

        User newUser = userService.getUser("marykonn@gmail.com");
        assertNotNull(newUser);
        assertEquals("Mary", newUser.getName());
        assertEquals("Konn", newUser.getSurname());
        assertEquals("marykonn@gmail.com", newUser.getEmail());
        assertEquals("password", newUser.getPassword());
    }

    @Test
    public void testStartExistingUser() throws Exception {
        String simulatedUserInput = "2\nangelina@gmail.com\npassword\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        // Accessing the private userService field
        Field userServiceField = UserRegistration.class.getDeclaredField("userService");
        userServiceField.setAccessible(true);
        UserService userService = (UserService) userServiceField.get(userRegistration);
    }
}
