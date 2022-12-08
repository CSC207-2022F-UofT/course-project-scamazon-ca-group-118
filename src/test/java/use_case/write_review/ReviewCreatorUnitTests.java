package use_case.write_review;

import database.GetUser;
import database.RegisterGatewayImplementation;
import entities.User;
import entities.Cart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewCreatorUnitTests {
    User user = new User(
            1, "user",
            "12345",
            "user@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());

    @Test
    void testReviewCreator() throws IOException {
        if(Objects.isNull(new GetUser().getUserWithUsername(user.getUsername()))){
            new RegisterGatewayImplementation().createUser(user.getUsername(), user.getEmail(), user.getPassword());
        }
        new ReviewCreator().createReview(user, 4);
        List<Integer> reviews = new GetUser().getUserWithUsername(user.getUsername()).getREVIEWS();
        assert reviews.get(reviews.size() - 1) == 4;
    }
}
