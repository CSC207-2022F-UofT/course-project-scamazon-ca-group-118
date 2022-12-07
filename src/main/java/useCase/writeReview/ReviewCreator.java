package useCase.writeReview;

import database.AddReview;
import entities.User;

import java.io.IOException;

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
     * @param reviewed the reviewed of the new review, and the User to whom this review will be added
     * @param rating   the rating value of the new review
     */
    public void createReview(User reviewed, int rating) throws IOException {
        new AddReview().addReview(reviewed, rating);
    }
}