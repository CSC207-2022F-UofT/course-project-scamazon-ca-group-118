package forms;

import use_case.write_review.ReviewResponseModel;
import use_case.write_review.ReviewRequestModel;

import java.util.Objects;


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
    protected void submitForm(){
        if (this.validateForm()) {
            ReviewRequestModel requestModel = new ReviewRequestModel(REVIEWER_USERNAME,
                    REVIEWED_USERNAME,
                    RATING);
            if(Objects.isNull(responseModel)){
                responseModel = new ReviewResponseModel(requestModel);
            }
        }
    }

    public String getMessage(){
        this.submitForm();
        if (this.validateForm()) {
            return responseModel.getMessage();
        } else if (this.REVIEWED_USERNAME.length() == 0) {
            return "Please enter the username of the User you wish to review";
        } else{
            return "You must enter a rating";
        }
    }

    public ReviewResponseModel getResponseModel() {
        return responseModel;
    }
}
