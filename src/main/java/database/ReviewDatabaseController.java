package database;

import database.ReviewDatabaseGateway;
import features.Cart;
import entities.User;

import java.util.ArrayList;

/**
 * The ReviewDatabaseController class retrieves the user with the specified username from the database
 */
public class ReviewDatabaseController implements ReviewDatabaseGateway {
    /**
     * the user retrieved from the database whose username is username, or an empty user if no user has username
     */
    User user;

    /**
     * Creates an instance of ReviewDatabaseController whose user attribute is the User from the database whose
     * username is username. If no such user exists, the user attribute is an empty User.
     *
     * @param username the username to be searched for in the database
     */
    public ReviewDatabaseController(String username) {
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
        //Dummy method that returns an empty user if username isn't user1 or user2. returns set user
        // if username is user1 or user2 just for tests
        if (username.equals("user1")) {
            return new User("user1",
                    "12345",
                    1,
                    "user1@gmail.com",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Cart());
        } else if (username.equals("user2")) {
            return new User("user2",
                    "678910",
                    2,
                    "user2@gmail.com",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Cart());
        } else {
            return new User("",
                    "",
                    0,
                    "",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Cart());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
