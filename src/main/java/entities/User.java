package entities;

import java.io.IOException;
import java.util.ArrayList;

import use_case.create_listing.ListingCreator;
import use_case.write_review.ReviewCreator;

public class User {
    private String username;
    private String password;
    private final int ID;
    private String email;
    private final ArrayList<Integer> REVIEWS;
    private ArrayList<Listing> listings;
    private Cart cart;
    private static int nextId = 0;

    /**
     * constructor for a user object
     * @param id id of user
     * @param username username of user
     * @param password password of user
     * @param email email of user
     * @param reviews reviews of user
     * @param listings listings user are selling
     * @param cart items in user's cart
     */
    public User(int id, String username, String password, String email, ArrayList<Integer> reviews,
                ArrayList<Listing> listings, Cart cart) {
        this.username = username;
        this.password = password;
        this.ID = id;
        this.email = email;
        this.REVIEWS = reviews;
        this.listings = listings;
        this.cart = cart;
    }

    public static int getNextId() {
        return nextId++;
    }

    public static void setNextId(int id) {
        nextId = id;
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
        return this.ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public ArrayList<Listing> getListings() {
        return listings;
    }

    public void setListings(ArrayList<Listing> listings) {
        this.listings = listings;
    }

    public ArrayList<Integer> getREVIEWS() {
        return this.REVIEWS;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    /**
     * creates a listing given title, price, description, image
     * @param title title of listing
     * @param price price of listing
     * @param description description of listing
     * @param image image of the listing
     * @throws IOException throws in case of IOException
     */
    public void createListing(String title, float price, String description, String image) throws IOException {
        new ListingCreator().createListing(this, title, price, description, image);
    }


    /**
     * removes a listing from the user's listings that they are selling
     *
     * @param listing listing object to be removed from user listings
     */
    public void removeListing(Listing listing) {
        int indexToRemove = -1;
        for (int i = 0; i < getListings().size(); i++) {
            if (getListings().get(i).getId() == listing.getId()) {
                indexToRemove = i;
            }
        }
        if (indexToRemove > -1) {
            listings = getListings();
            listings.remove(indexToRemove);
            setListings(listings);
        }
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

    public void removeFromCart(Listing listing) {
        this.getCart().removeItem(listing);
    }


    public void writeReview(User reviewed, int rating) throws IOException {
        new ReviewCreator().createReview(reviewed, rating);
    }


    /**
     * Adds a review to this User's list of reviews
     *
     * @param review the review to be added to this User's reviews
     */
    public void addReview(int review) {
        this.REVIEWS.add(review);
    }

    /**
     * calculates the average integer rating earned by this User
     * 0
     *
     * @return the average rating of all this User's reviews
     */
    public double calculateRating() {
        double rating = 0.0;
        for (int review : REVIEWS) {
            rating += review;
        }
        rating /= REVIEWS.size();
        rating = (double) (Math.round(rating * 100)) / 100;
        return rating;
    }

    /**
     * removes from the cart given a listing ID
     * @param listingID ID of the listing to remove
     * @return returns true if listing was removed from cart
     */
    public boolean removeFromCartByID(int listingID) {
        ArrayList<Listing> listings = this.getCart().getItems();
        for (Listing listing : listings) {
            if (listing.getId() == listingID) {
                listings.remove(listing);
                this.setCart(new Cart(listings));
                return true;
            }
        }
        return false;
    }

    /**
     * Given a user objects, checks to see if the current user and the user given are the exact same
     * @param user user object that is passed in
     * @return returns true if they are the same objects
     */
    public boolean equals(User user) {
        if (this.getID() == user.getID() &&
                this.getUsername().equals(user.getUsername()) &&
                this.getPassword().equals(user.getPassword()) &&
                this.getEmail().equals(user.getEmail()) &&
                this.getREVIEWS().equals(user.getREVIEWS())
        ) {
            // check listings are equal, ORDER MATTERS
            if (this.getListings().size() != user.getListings().size()) {
                return false;
            }
            for (int i = 0; i < this.getListings().size(); i++) {
                if (!this.getListings().get(i).equals(user.getListings().get(i))) {
                    return false;
                }
            }
            // check carts are equal, ORDER MATTERS
            if (this.getCart().getItems().size() != user.getCart().getItems().size()) {
                return false;
            }
            for (int i = 0; i < this.getCart().getItems().size(); i++) {
                if (!this.getCart().getItems().get(i).equals(user.getCart().getItems().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

