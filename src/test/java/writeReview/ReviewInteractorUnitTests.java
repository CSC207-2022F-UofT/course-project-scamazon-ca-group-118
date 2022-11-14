package writeReview;

import org.junit.jupiter.api.Test;
import useCase.writeReview.ReviewInteractor;
import useCase.writeReview.ReviewRequestModel;

public class ReviewInteractorUnitTests {
    ReviewRequestModel userExistsRequestModel = new ReviewRequestModel("user1",
            "user2",
            4);
    ReviewRequestModel userDoesntExistRequestModel = new ReviewRequestModel("user1",
            "not user2",
            3);

    @Test
    void testReviewInteractorUserExists() {
        assert new ReviewInteractor(userExistsRequestModel).getMessage().equals("Review Successful");
    }

    @Test
    void testReviewInteractorUserDoesntExist() {
        assert new ReviewInteractor(userDoesntExistRequestModel).getMessage()
                .equals("Review Unsuccessful: No User exists with this username");
    }
}
