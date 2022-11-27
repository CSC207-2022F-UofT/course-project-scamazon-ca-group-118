package forms;

/**
 * TODO uncomment and implement these tests once Database implemented
public class ReviewFormUnitTests {
    @Test
    void testReviewFormUserDoesntExist() {
        assert new ReviewForm("user1", "not user2", 5).getMessage()
                .equals("Review Unsuccessful: No User exists with this username");
    }

    @Test
    void testReviewFromUserExists() {
        assert new ReviewForm("user1", "user2", 5).getMessage()
                .equals("Review Successful");
    }

    @Test
    void testReviewFormNoUserEntered() {
        assert new ReviewForm("user1", "", 5).getMessage()
                .equals("Please enter the username of the User you wish to review");
    }
}
 */
