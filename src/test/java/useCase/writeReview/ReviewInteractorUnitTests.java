package useCase.writeReview;


import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReviewInteractorUnitTests {
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
    ReviewRequestModel requestModel = new ReviewRequestModel("user1",
            "user2", 4);

    @Test
    void testReviewInteractorUserExists() {
        ReviewInteractor interactor = new ReviewInteractor(requestModel);
        interactor.setReviewer(user1);
        interactor.setReviewed(user2);
        assert interactor.getMessage().equals("Review Successful");
        assert user2.getREVIEWS().get(0) == 4;
    }

    @Test
    void testReviewInteractorUserDoesntExist() {
        ReviewInteractor interactor = new ReviewInteractor(requestModel);
        interactor.setReviewer(user1);
        assert interactor.getMessage().equals("Review Unsuccessful: No User exists with this username");
        assert user2.getREVIEWS().size() == 0;
    }
}

