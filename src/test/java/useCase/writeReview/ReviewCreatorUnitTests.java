package useCase.writeReview;

import entities.User;
import entities.Cart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReviewCreatorUnitTests {
    User user = new User(2, "user2",
            "678910",
            "user2@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());

    @Test
    void testReviewCreator() {
        new ReviewCreator().createReview(user, 4);
        assert user.getREVIEWS().size() == 1;
        assert user.getREVIEWS().get(0) == 4;
    }
}
