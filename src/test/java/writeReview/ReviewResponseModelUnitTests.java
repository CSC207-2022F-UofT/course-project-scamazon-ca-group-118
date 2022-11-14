package writeReview;

import org.junit.jupiter.api.Test;
import useCase.writeReview.ReviewRequestModel;
import useCase.writeReview.ReviewResponseModel;

public class ReviewResponseModelUnitTests {
    ReviewRequestModel userExistsRequestModel = new ReviewRequestModel("user1",
            "user2",
            4);
    ReviewRequestModel userDoesntExistRequestModel = new ReviewRequestModel("user1",
            "not user2",
            3);

    @Test
    void testResponseModelUserExists() {
        assert new ReviewResponseModel(userExistsRequestModel).getMessage().equals("Review Successful");
    }

    @Test
    void testResponseModelUserDoesntExist() {
        assert new ReviewResponseModel(userDoesntExistRequestModel).getMessage().
                equals("Review Unsuccessful: No User exists with this username");
    }
}
