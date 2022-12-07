package useCase.writeReview;


import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReviewResponseModelUnitTests {
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
    void testResponseModelUserExists() {
        ReviewRequestModel request_model =
                new ReviewRequestModel("user1", "user2", 4);
        ReviewResponseModel response_model = new ReviewResponseModel(request_model);
        ReviewInteractor interactor = response_model.getInteractor();
        interactor.setReviewer(user1);
        interactor.setReviewed(user2);
        String message = response_model.getMessage();
        assert(message.equals("Review Successful"));
        assert(user2.getReviews().get(0) == 4);
    }

    @Test
    void testResponseModelUserDoesntExist() {
        ReviewRequestModel request_model =
                new ReviewRequestModel("user1", "not user2", 3);
        ReviewResponseModel response_model = new ReviewResponseModel(request_model);
        ReviewInteractor interactor = response_model.getInteractor();
        interactor.setReviewer(user1);
        interactor.setReviewed(null);
        String message = response_model.getMessage();
        assert(message.equals("Review Unsuccessful: No User exists with this username"));
        assert(user2.getReviews().size() == 0);
    }
}
