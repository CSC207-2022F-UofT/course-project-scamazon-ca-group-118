package database;

import entities.User;

/**
 * The GetUserWithUsername class will have a final attribute USER holds the User from the database with the
 * specified username, or an empty User if no user has this username
 */
public class GetUser implements ReviewDatabaseGateway {

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
    //TODO: Rework this function so it uses Database Interactor instead
    public User getUserWithUsername(String username) {
        return null;
    }
}
