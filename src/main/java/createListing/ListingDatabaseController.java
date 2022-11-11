package createListing;

import entities.User;

/**
 * The ListingDatabaseController class retrieves the user with the specified username from the database
 */
public class ListingDatabaseController implements ListingDatabaseGateway {
    /**
     * the user retrieved from the database whose username is username, or an empty user if no user has username
     */
    User user;
    @Override
    public User getUserWithUsername(String username) {
        return null;
    }
}
