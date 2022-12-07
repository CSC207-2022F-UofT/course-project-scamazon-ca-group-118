package database;

import entities.User;

import java.util.Objects;

/**
 * Represents a UserExists class that will be used to check if a given user is not null i.e. exists
 */
public class UserExists {
    private final User user;

    /**
     * Constructor for the UserExists class
     *
     * @param user the user that will be checked for existence
     */
    public UserExists(User user) {
        this.user = user;
    }

    /**
     * Checks if the user passed to the constructor is not null
     *
     * @return true iff the user passed to the constructor is not null
     */
    public boolean checkExists() {
        return !Objects.isNull(this.user);
    }
}
