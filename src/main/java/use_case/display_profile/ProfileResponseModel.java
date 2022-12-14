package use_case.display_profile;

import java.util.ArrayList;

// This class is in the application business rules layer of clean architecture.

/**
 * This is the response model for the display profile page use case.
 */
public class ProfileResponseModel {
    private String username;
    private String email;
    private double rating;
    private ArrayList<Integer> reviews;

    /**
     * The ProfileResponseModel constructor that contains all the user attributes we need to display on
     * the profile page.
     * @param username The user's username.
     * @param email The user's email.
     * @param rating The user's rating.
     * @param reviews The user's reviews about themselves.
     */
    public ProfileResponseModel(String username, String email, double rating, ArrayList<Integer> reviews) {
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.reviews = reviews;
    }

    /**
     * Gets the username from this ProfileResponseModel.
     * @return The username associated with this ProfileResponseModel.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of this ProfileResponseModel.
     * @param newUsername The new username to update this ProfileResponseModel with.
     */

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    /**
     * Gets the email from this ProfileResponseModel.
     * @return The email associated with this ProfileResponseModel.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email of this ProfileResponseModel.
     * @param newEmail The new email to update this ProfileResponseModel with.
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
     * Gets the rating from this ProfileResponseModel.
     * @return The rating associated with this ProfileResponseModel.
     */
    public double getRating() {
        return this.rating;
    }

    /**
     * Sets the rating of this ProfileResponseModel.
     * @param newRating The new rating to update this ProfileResponseModel with.
     */
    public void setRating(double newRating) {
        this.rating = newRating;
    }

    /**
     * Gets the reviews from this ProfileResponseModel.
     * @return The list of reviews associated with this ProfileResponseModel.
     */
    public ArrayList<Integer> getReviews() {
        return this.reviews;
    }

    /**
     * Sets the reviews of this ProfileResponseModel.
     * @param newReviews The new list of reviews to update this ProfileResponseModel with.
     */
    public void setReviews(ArrayList<Integer> newReviews) {
        this.reviews = newReviews;
    }
}
