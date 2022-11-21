package entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
/**
public class UserUnitTests {
    @Test
    void testCalculateRating(){
        User user1 = new User(1, "user1",
                "1234",
                "user1@gmail.com",
                new ArrayList<>(),
                new ArrayList<>(),
                new Cart());
        User user2 = new User(2, "user2",
                "5678",
                "user2@gmail.com",
                new ArrayList<>(),
                new ArrayList<>(),
                new Cart());
        Review review1 = new Review(user1, user2, 1);
        Review review2 = new Review(user1, user2, 1);
        Review review3 = new Review(user1, user2, 5);
        Review review4 = new Review(user1, user2, 5);
        Review review5 = new Review(user1, user2, 5);
        Review review6 = new Review(user1, user2, 3);
        user2.addReview(review1);
        user2.addReview(review2);
        user2.addReview(review3);
        user2.addReview(review4);
        user2.addReview(review5);
        user2.addReview(review6);
        assert user2.calculateRating() == 3;
    }
}
 */
