package forms;

import useCase.writeReview.ReviewResponseModel;
import useCase.writeReview.ReviewRequestModel;

import java.io.IOException;

public class ReviewForm extends Form {
    private final String REVIEWER_USERNAME;
    private final String REVIEWED_USERNAME;
    private final int RATING;
    private ReviewResponseModel responseModel;

    public ReviewForm(String reviewerUsername, String reviewedUsername, int rating) {
        super("Review Form");
        this.REVIEWER_USERNAME = reviewerUsername;
        this.REVIEWED_USERNAME = reviewedUsername;
        this.RATING = rating;
    }

    @Override
    protected boolean validateForm() {
        return this.REVIEWED_USERNAME.length() > 0 && this.RATING > 0;
    }

    @Override
    protected void submitForm() throws IOException {
        if (this.validateForm()) {
            ReviewRequestModel requestModel = new ReviewRequestModel(REVIEWER_USERNAME,
                    REVIEWED_USERNAME,
                    RATING);
            responseModel = new ReviewResponseModel(requestModel);
        }
    }

    public String getMessage() throws IOException {
        this.submitForm();
        if (this.validateForm()) {
            return responseModel.getMessage();
        } else if (this.REVIEWED_USERNAME.length() == 0) {
            return "Please enter the username of the User you wish to review";
        } else{
            return "You must enter a rating";
        }
    }
}
