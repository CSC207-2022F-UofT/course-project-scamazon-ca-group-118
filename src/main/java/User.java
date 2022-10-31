import java.util.List;
import features.Cart;
import features.Listing;
import features.Review;

public class User {
    private String username;
    private String password;
    private int id;
    private String email;
    private List<Review> reviews;
    private List<Listing> listings;
    private Cart cart;
    private int rating;

    public User(String username, String password, int id, String email, List<Review> reviews,
                List<Listing> listings, Cart cart, int rating) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.reviews = reviews;
        this.listings = listings;
        this.cart = cart;
        this.rating = rating;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    //TODO: Implement
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void removeListing() {

    }

    public void createListing() {

    }

    public void addCart() {

    }
    public void removeCart() {

    }
    public void writeReview() {

    }
    public void removeReview() {

    }


}
