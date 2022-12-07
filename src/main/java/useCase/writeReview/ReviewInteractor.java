package useCase.writeReview;

import database.GetUser;
import database.UserExists;
import entities.User;

import java.io.IOException;

/**
 * The ReviewInteractor class takes in a ReviewRequestModel and generates the necessary data for the
 * ReviewResponseModel
 */
public class ReviewInteractor {
    private User reviewer;
    private User reviewed;
    private int rating;
    private String message;

    /**
     * The constructor for the ReviewInteractor class
     *
     * @param requestModel the request model that's data will be manipulated
     */
    public ReviewInteractor(ReviewRequestModel requestModel){
        this.reviewer = getUserWithUsername(requestModel.getReviewerUsername());
        this.reviewed = getUserWithUsername(requestModel.getReviewedUsername());
        this.rating = requestModel.getRating();
    }

    /**
     * Returns the user from the database with the given username
     *
     * @param username the username being searched for
     * @return the user with the given username
     */
    private User getUserWithUsername(String username) {
        try{
            return new GetUser().getUserWithUsername(username);
        }catch(IOException e){
            this.message = "**DATABASE ERROR**";
            return null;
        }
    }

    /**
     * Checks if the user is non-empty i.e. exists.
     *
     * @param user the user being checked for existence
     * @return true iff the user exists
     */
    private boolean userExists(User user) {
        return new UserExists(user).checkExists();
    }

    /**
     * Creates a review with the given reviewer, reviewed, and rating as the same fields in the new Review.
     * Then, adds this new review reviewed's reviews
     */
    private void createReview() {
        try{
            this.reviewer.writeReview(reviewed, rating);
        }catch(IOException e){
            this.message = "**DATABASE ERROR**";
        }
    }

    /**
     * Determines whether the Review can be successfully created i.e. the reviewed user exists.
     * If possible, it creates the review then returns a success message.
     * If the review can't be created, it returns a failure message
     *
     * @return String that communicates whether the review was successfully created
     */
    public String getMessage() {
        if (userExists(reviewed)) {
            this.createReview();
            this.message = "Review Successful";
        } else {
            if(this.message == null){
                this.message = "Review Unsuccessful: No User exists with this username";
            }
        }
        return this.message;
    }

    public int getRating() {
        return rating;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public void setReviewed(User reviewed) {
        this.reviewed = reviewed;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
