package login;

import features.Cart;
import features.Listing;
import entities.User;
import features.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * The LoginDatabaseController class retrieves the user with the specified username from the database
 */
public class LoginDatabaseController implements LoginDatabaseGateway {
    /**
     * the user retrieved from the database whose username is username, or an empty user if no user has username
     */
    User user;

    /**
     * Creates an instance of LoginDatabaseController whose user attribute is the User from the database whose
     * username is username. If no such user exists, the user attribute is an empty User.
     *
     * @param username the username to be searched for in the database
     */
    public LoginDatabaseController(String username) {
        this.user = getUserWithUsername(username);
    }

    /**
     * TODO: implement this method. It is currently a dummy.
     * Retrieves the User whose username is username from the database, and returns it. If no user has this
     * username, return an empty user.
     *
     * @param username the username to searched for in the database
     * @return either the User whose username is username, or an empty user
     */
    @Override
    public User getUserWithUsername(String username) {
        //Dummy method that returns an empty user if username isn't clare. returns set user if username is clare
        //just for tests
        List<Review> emptyReviews = new ArrayList<>();
        List<Listing> emptyListing = new ArrayList<>();
        Cart emptyCart = new Cart();
        if (username.equals("clare")) {
            return new User("clare",
                    "12345",
                    1,
                    "clare@gmail.com",
                    emptyReviews,
                    emptyListing,
                    emptyCart);
        } else {
            return new User("", "", 0, "", emptyReviews, emptyListing, emptyCart);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}