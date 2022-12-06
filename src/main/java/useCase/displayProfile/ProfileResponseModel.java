package useCase.displayProfile;

import java.util.ArrayList;

// This class is in the application business rules layer of clean architecture.

/**
 * This is the response model for the display profile page use case.
 */
public class ProfileResponseModel {
    private String username;
    private String email;
    private double rating;
    private String profilePic;
    private ArrayList<Integer> reviews;

    /**
     * The ProfileResponseModel constructor that contains all the user attributes we need to display on
     * the profile page.
     * @param username The user's username.
     * @param email The user's email.
     * @param rating The user's rating.
     * @param profilePic The user's profilePic.
     * @param reviews The user's reviews about themselves.
     */
     
    public ProfileResponseModel(String username, String email, double rating, String profilePic, ArrayList<Integer> reviews) {
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.profilePic = profilePic; // TODO take profilePic out if we don't have time to implement db method for it.
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
     * Gets the profile picture from this ProfileResponseModel.
     * @return The profile picture image path associated with this ProfileResponseModel.
     */
    public String getProfilePic() {
        return this.profilePic;
    }

    /**
     * Sets the profile picture of this ProfileResponseModel.
     * @param imagePath The new profile picture path to update this ProfileResponseModel with.
     */
    public void setProfilePic(String imagePath) {
        this.profilePic = imagePath;
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
