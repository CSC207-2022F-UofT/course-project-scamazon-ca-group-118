package database;

import entities.User;

/**
 * The ListingDatabaseController class retrieves the user with the specified username from the database
 */
public class ListingDatabaseController implements ListingDatabaseGateway {
    /**
     * the user retrieved from the database whose username is username, or an empty user if no user has username
     */
    User user;

    /**
     * Creates an instance of ListingDatabaseController whose user attribute is the User from the database whose
     * username is username. If no such user exists, the user attribute is an empty User.
     *
     * @param username the username to be searched for in the database
     */
    public ListingDatabaseController(String username) {
        this.user = getUserWithUsername(username);
    }

    /**
     * Retrieves the User whose username is username from the database, and returns it. If no user has this
     * username, return an empty user.
     *
     * @param username the username to searched for in the database
     * @return either the User whose username is username, or an empty user
     */
    @Override
    public User getUserWithUsername(String username) {

        //TODO: Implement with database methods
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
