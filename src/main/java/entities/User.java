package entities;

import java.util.List;

import useCase.createListing.ListingCreator;
import useCase.writeReview.ReviewCreator;

public class User {
    public static User currentUser;
    private String username;
    private String password;
    private int id;
    private String email;
    private List<Integer> reviews;
    private List<Listing> listings;
    private Cart cart;
    private static int nextID = 0;

    public User(int id, String username, String password, String email, List<Integer> reviews,
                List<Listing> listings, Cart cart) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.reviews = reviews;
        this.listings = listings;
        this.cart = cart;
        this.setCurrentUser();
    }

    public static int getNextID() {
        return nextID++;
    }

    //Precondition: new User instances will always be the current User logged in.
    public void setCurrentUser() {
        currentUser = this;
    }

    static User getCurrentUser() {
        return currentUser;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public Cart getCart() {
        return cart;
    }

    //TODO: Implement
    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public void createListing(String title, float price, String description, List<String> images) {
        new ListingCreator().createListing(this, title, price, description, images);
    }


    public void removeListing(Listing listing) {
    //Checkout is going to use this
    }

    /**
     * addListing takes in a listing and adds it to the users list of listings
     *
     * @param listing the listing to be added to the user
     */
    public void addListing(Listing listing) {
        listings.add(listing);
    }


    public void addToCart(Listing listing) {
        this.getCart().addItem(listing);
    }
    public void removeFromCart() {

    }


    /**
     * Creates a new review with this User as the reviewer, and the specified reviewer and rating
     * then adds it to the reviewed User's reviews
     *
     * @param reviewed the User being reviewed/the User whose reviews the new Review will be added to
     * @param rating   the rating given to the User being reviewed
     */
    // TODO: Change writeReview, removeReview to not need Review class anymore, only integers
//    public void writeReview(User reviewed, int rating) {
//        new ReviewCreator().createReview(this, reviewed, rating);
//
//    }
//
//    /**
//     * Removes a review from this User's list of reviews
//     *
//     * @param toBeRemoved the Review to be removed from this User's reviews
//     */
//    public void removeReview(Review toBeRemoved) {
//        this.reviews.remove(toBeRemoved);
//    }
//
//    /**
//     * Adds a review to this User's list of reviews
//     *
//     * @param review the review to be added to this User's reviews
//     */
//    public void addReview(Review review) {
//        this.reviews.add(review);
//    }
//
//
//    public void removeReview() {
//    }
//
    /**
     * calculates the average integer rating earned by this User
     *
     * @return the average rating of all this User's reviews
     */
    public int calculateRating() {
        double rating = 0;
        for (int review : reviews) {
            rating += review;
        }
        rating /= reviews.size();
        return (int) rating;
    }


}

