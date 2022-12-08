package use_case.write_review;

/**
 * Represents a ReviewRequestModel object that holds all data to be passed to the ReviewInteractor
 */
public class ReviewRequestModel {
    private final String REVIEWER_USERNAME;
    private final String REVIEWED_USERNAME;
    private int rating;

    /**
     * Constructor for a ReviewRequestModel
     *
     * @param reviewerUsername the username of the user writing the review
     * @param reviewedUsername the username of the user being reviewed
     * @param rating           the rating being given to the user being reviewed
     */
    public ReviewRequestModel(String reviewerUsername, String reviewedUsername, int rating) {
        this.REVIEWER_USERNAME = reviewerUsername;
        this.REVIEWED_USERNAME = reviewedUsername;
        this.rating = rating;
    }

    public String getReviewedUsername() {
        return REVIEWED_USERNAME;
    }

    public String getReviewerUsername() {
        return REVIEWER_USERNAME;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}

