package useCase.writeReview;

import entities.User;
import features.Review;

/**
 * Represents a ReviewCreator object
 */
public class ReviewCreator {

    /**
     * constructor for ReviewCreator class
     */
    public ReviewCreator() {
    }

    /**
     * createReview method takes in a reviewer User, reviewed User, and rating to create a review with the
     * specified values, and adds this review to reviewed's reviews.
     *
     * @param reviewer the reviewer of the new review
     * @param reviewed the reviewed of the new review, and the User to whom this review will be added
     * @param rating   the rating value of the new review
     */
    public void createReview(User reviewer, User reviewed, int rating) {
        Review review = new Review(reviewer, reviewed, rating);
        reviewed.addReview(review);
    }
}