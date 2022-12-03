package useCase.writeReview;

import entities.User;
import entities.Cart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReviewCreatorUnitTests {
    User user1 = new User(
            1,"user1",
            "12345",
            "user1@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    User user2 = new User(2, "user2",
            "678910",
            "user2@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());

    @Test
    void testReviewCreator() {
        new ReviewCreator().createReview(user1, user2, 4);
        //assert user2.getReviews().size() == 1;
        //assert user2.getReviews().get(0).getReviewer() == user1;
    }
}
