package database;

import entities.User;

public interface CheckoutDatabaseGateway {
    /**
     * should retrieve the User with the specified username from the database, or an empty User if no user has
     * this username
     *
     * @param username the username that will be searched for in the database
     * @return User whose username is username, or an empty User
     */
    User getUserWithUsername(String username);
}