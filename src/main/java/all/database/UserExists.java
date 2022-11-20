package all.database;

import all.entities.User;

/**
 * Represents a UserExists class that will be used to check if a given user is non-empty i.e. exists.
 * Preconditions: only an empty user may have an empty string as password
 */
public class UserExists {
    private final String PASSWORD;

    /**
     * Constructor for the UserExists class
     *
     * @param user the user that will be checked for existence
     */
    public UserExists(User user) {
        this.PASSWORD = user.getPassword();
    }

    /**
     * Checks if the user passed to the constructor is non-empty or exists
     *
     * @return true iff the user passed to the constructor is non-empty
     */
    public boolean checkExists() {
        return !this.PASSWORD.equals("");
    }
}
