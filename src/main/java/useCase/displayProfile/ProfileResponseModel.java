package useCase.displayProfile;

import java.util.ArrayList;

// This class is in the application business rules layer of clean architecture.

public class ProfileResponseModel {
    private String username;
    private String email;
    private double rating;
    private String profilePic;
    private ArrayList<Integer> reviews;

    /**
     * The ProfileResponseModel constructor that contains all of the user attributes we need to display on
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
        this.profilePic = profilePic;
        this.reviews = reviews;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(int newRating) {
        this.rating = newRating;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public void setProfilePic(String imagePath) {
        this.profilePic = imagePath;
    }

    public ArrayList<Integer> getReviews() {
        return this.reviews;
    }

    public void setReviews(ArrayList<Integer> newReviews) {
        this.reviews = newReviews;
    }
}
