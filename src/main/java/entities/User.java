package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import useCase.createListing.ListingCreator;
import useCase.writeReview.ReviewCreator;

public class User {
    private String username;
    private String password;
    private int id;
    private String email;
    private ArrayList<Integer> reviews;
    private ArrayList<Listing> listings;
    private Cart cart;
    private static int nextId = 0;

    public User(int id, String username, String password, String email, ArrayList<Integer> reviews,
                ArrayList<Listing> listings, Cart cart) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.reviews = reviews;
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
        return this.id;
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

    public ArrayList<Integer> getReviews() {
        return this.reviews;
    }

    public Cart getCart() {
        return cart;
    }

    //TODO: Implement
    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public void createListing(String title, float price, String description, String image) throws IOException {
        new ListingCreator().createListing(this, title, price, description, image);
    }


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


    public void writeReview(User reviewed, int rating) {
        new ReviewCreator().createReview(reviewed, rating);
    }

    /**
     * Removes a review from this User's list of reviews
     *
     * @param toBeRemoved the Review to be removed from this User's reviews
     */
    public void removeReview(int toBeRemoved) {
        this.reviews.remove(toBeRemoved);
    }

    /**
     * Adds a review to this User's list of reviews
     *
     * @param review the review to be added to this User's reviews
     */
    public void addReview(int review) {
        this.reviews.add(review);
    }

    /**
     * calculates the average integer rating earned by this User
     * 0
     *
     * @return the average rating of all this User's reviews
     */
    public double calculateRating() {
        double rating = 0.0;
        for (int review : reviews) {
            rating += review;
        }
        rating /= reviews.size();
        rating = (double) (Math.round(rating * 100)) / 100;
        return rating;
    }

    // TODO test
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

    public boolean equals(User user) {
        if (this.getID() == user.getID() &&
                this.getUsername().equals(user.getUsername()) &&
                this.getPassword().equals(user.getPassword()) &&
                this.getEmail().equals(user.getEmail()) &&
                this.getReviews().equals(user.getReviews())
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

