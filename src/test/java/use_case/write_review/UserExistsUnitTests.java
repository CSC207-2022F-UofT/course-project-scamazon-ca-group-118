package use_case.write_review;

import database.UserExists;
import entities.User;
import entities.Cart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserExistsUnitTests {
    static User clare = new User(1, "clare",
            "12345",
            "clare@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());

    @Test
    void testUserExistsNo() {
        assertFalse(new UserExists(null).checkExists());
    }

    @Test
    void testUserExistsYes() {
        assert (new UserExists(clare).checkExists());
    }
}
