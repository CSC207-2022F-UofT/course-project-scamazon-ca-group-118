package writeReview;

/**
 * Represents a ReviewRequestModel object that holds all data to be passed to the ReviewInteractor
 */
public class ReviewRequestModel {
    private String reviewerUsername;
    private String reviewedUsername;
    private int rating;

    /**
     * Constructor for a ReviewRequestModel
     *
     * @param reviewerUsername the username of the user writing the review
     * @param reviewedUsername the username of the user being reviewed
     * @param rating           the rating being given to the user being reviewed
     */
    public ReviewRequestModel(String reviewerUsername, String reviewedUsername, int rating) {
        this.reviewerUsername = reviewerUsername;
        this.reviewedUsername = reviewedUsername;
        this.rating = rating;
    }

    public String getReviewedUsername() {
        return reviewedUsername;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewedUsername(String reviewedUsername) {
        this.reviewedUsername = reviewedUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

}

