package use_case.write_review;

import database.GetUser;
import database.RegisterGatewayImplementation;
import entities.Cart;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ReviewResponseModelUnitTests {
    private static User user = new User(
            1, "user",
            "12345",
            "user@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    private static User reviewer = new User(
            2, "reviewer",
            "password",
            "reviewer@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    @BeforeAll
    public static void setUp() throws IOException {
        if(Objects.isNull(new GetUser().getUserWithUsername(user.getUsername()))){
            new RegisterGatewayImplementation().createUser(user.getUsername(), user.getEmail(), user.getPassword());
        }
        if(Objects.isNull(new GetUser().getUserWithUsername(reviewer.getUsername()))){
            new RegisterGatewayImplementation().createUser(reviewer.getUsername(),
                    reviewer.getEmail(), reviewer.getPassword());
        }
    }
    @Test
    void testResponseModelUserExists(){
        assert new ReviewResponseModel(new ReviewRequestModel(reviewer.getUsername(), user.getUsername(),
                3)).getMessage().equals("Review Successful");
    }
    @Test
    void testResponseModelUserDoesntExist(){
        assert new ReviewResponseModel(new ReviewRequestModel(reviewer.getUsername(),
                "UserThatShouldNeverExistDoNotRegister", 3)).
                getMessage().equals("Review Unsuccessful: No User exists with this username");
    }
}
