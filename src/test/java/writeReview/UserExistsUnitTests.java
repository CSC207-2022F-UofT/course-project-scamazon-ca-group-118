package writeReview;

import entities.User;
import features.Cart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserExistsUnitTests {
    static User clare = new User("clare",
            "12345",
            1,
            "clare@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    static User emptyUser = new User("",
            "",
            0,
            "",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    @Test
    void testUserExistsNo(){
        assertFalse(new UserExists(emptyUser).checkExists());
    }

    @Test
    void testUserExistsYes(){
        assert(new UserExists(clare).checkExists());
    }
}
