package writeReview;

import forms.ReviewForm;
import org.junit.jupiter.api.Test;

public class ReviewPresenterUnitTests {
    @Test
    void testReviewPresenterUserExists(){
        ReviewForm form = new ReviewForm("user1", "user2", 3);
        assert new ReviewPresenter(form).getMessage().equals("Review Successful");
    }

    @Test
    void testReviewPresenterUserDoesntExist(){
        ReviewForm form = new ReviewForm("user1", "not user2", 2);
        assert new ReviewPresenter(form).getMessage().equals("Review Unsuccessful: No User exists with this username");
    }

    @Test
    void testUserPresenterNoUserEntered(){
        ReviewForm form = new ReviewForm("user1", "", 1);
        assert new ReviewPresenter(form).getMessage()
                .equals("Please enter the username of the User you wish to review");
    }
}
