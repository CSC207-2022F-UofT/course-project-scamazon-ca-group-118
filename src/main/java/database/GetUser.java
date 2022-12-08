package database;

import entities.User;

import java.io.IOException;

/**
 * The GetUserWithUsername class will have a final attribute USER holds the User from the database with the
 * specified username, or an empty User if no user has this username
 */
public class GetUser implements LoginDatabaseGateway {

    /**
     * Creates an instance of GetUserWithUsername
     */
    public GetUser() {
    }

    /**
     * Returns the user from the database whose username is user, or an empty user if no user has this username
     *
     * @param username the username that will be searched for in the database
     * @return either the User with username, or an empty user
     */

    @Override
    public User getUserWithUsername(String username) throws IOException {
        return new DatabaseController().getUserWithUsername(username);
    }
}
