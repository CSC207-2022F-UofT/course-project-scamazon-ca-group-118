package all.useCase.writeReview;

import org.junit.jupiter.api.Test;

public class ReviewRequestModelUnitTests {
    @Test
    void testReviewRequestModel() {
        ReviewRequestModel requestModel = new ReviewRequestModel(
                "user1",
                "user2",
                2);
        assert requestModel.getReviewerUsername().equals("user1");
        assert requestModel.getReviewedUsername().equals("user2");
        assert requestModel.getRating() == 2;
    }
}
