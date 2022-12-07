package useCase.writeReview;


import entities.Cart;
import entities.User;
import forms.ReviewForm;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReviewPresenterUnitTests {
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
    void testReviewPresenterUserExists() {
        ReviewForm form = new ReviewForm("user1", "user2", 3);
        form.getMessage();
        form.getResponseModel().getInteractor().setReviewer(user1);
        form.getResponseModel().getInteractor().setReviewed(user2);
        String message = new ReviewPresenter(form).getMessage();
        assert message.equals("Review Successful");
        assert user2.getREVIEWS().get(0) == 3;
    }

    @Test
    void testReviewPresenterUserDoesntExist() {
        ReviewForm form = new ReviewForm("user1", "not user2", 2);
        form.getMessage();
        form.getResponseModel().getInteractor().setReviewer(user1);
        form.getResponseModel().getInteractor().setReviewed(null);
        assert(user2.getREVIEWS().size() == 0);
        String message = new ReviewPresenter(form).getMessage();
        assert message.equals("Review Unsuccessful: No User exists with this username");
    }

    @Test
    void testUserPresenterNoUserEntered() {
        ReviewForm form = new ReviewForm("user1", "", 1);
        assert new ReviewPresenter(form).getMessage()
                .equals("Please enter the username of the User you wish to review");
    }

    @Test
    void testUserPresenterNoReviewEntered(){
        ReviewForm form = new ReviewForm("user1", "user2", 0);
        assert new ReviewPresenter(form).getMessage().equals("You must enter a rating");
    }
}
