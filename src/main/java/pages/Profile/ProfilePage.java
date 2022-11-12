package pages.profile;
import pages.Page;
import entities.User;
import features.Review;
import java.util.List;

// This class now seems repetitive and unnecessary
// but may be needed for the GUI so I commented it out for now until I implement the GUI.

public class ProfilePage extends Page {
    //private User user;
    //private String username;
    //private String email;
    //private List<Review> reviews;
    //private int rating;
    //private String profilePic;
//
    public ProfilePage(String title) {
        super(title);
    }
    ///**
    // * The ProfilePage constructor.
    // * @param title The title of the profile page.
    // * @param user The current user of the program.
    // * @param profilePic The path of the image for this user's profile picture.
    // */
    //public ProfilePage(String title, User user, String profilePic) {
    //    super(title);
    //    this.user = user;
    //    this.username = user.getUsername();
    //    this.email = user.getEmail();
    //    this.reviews = user.getReviews();
    //    this.rating = user.calculateRating(); // Uses the implementation in User class.
    //    this.profilePic = profilePic;
    //}
//
    ///**
    // * The ProfilePage constructor with no profilePic, so their picture will be a default picture and profilePic is
    // * null.
    // * @param title The title of the profile page.
    // * @param user The current user of the program.
    // */
    //public ProfilePage(String title, User user) {
    //    super(title);
    //    this.user = user;
    //    this.username = user.getUsername();
    //    this.email = user.getEmail();
    //    this.reviews = user.getReviews();
    //    this.rating = user.calculateRating(); // Uses the implementation in User class.
    //    this.profilePic = null;
    //}
//
    //// Note there are no setters for username, email, reviews, or rating since they all depend on user and
    //// shouldn't be changed.
    //public User getUser() {
    //    return this.user;
    //}
//
    //public String getUsername() {
    //    return this.username;
    //}
//
    //public String getEmail() {
    //    return this.email;
    //}
//
    //public List<Review> getReviews() {
    //    return this.reviews;
    //}
//
    //public int getRating() {
    //    return this.rating;
    //}
    //public String getProfilePic() {
    //    return this.profilePic;
    //}
//
    //public void setProfilePic(String imagePath) {
    //    this.profilePic = imagePath;
    //}
//
    ///**
    // * TODO
    // * This method displays all of the pertinent information to the profile page that we collected from the current
    // * user object when the profile button is clicked. I'm not sure what we are doing for clean architecture,
    // * but I think this would be in some kind of controller class for our program.
    // */
    //public void displayProfile() {
//
    //}
//
}
