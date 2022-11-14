package login;

import entities.User;

/**
 * Represents a UserExists object. This object will be used to check if the given User is non-empty, i.e. exists.
 */
public class UserExists {
    private final String PASSWORD;

    /**
     * A constructor for the UserExists class
     *
     * @param user the User that's existence is being checked
     */
    public UserExists(User user) {
        this.PASSWORD = user.getPassword();
    }

    /**
     * Checks whether the User given as a parameter to the constructor is non-empty i.e. exists
     *
     * @return boolean of whether the User given to the constructor exists
     */
    public boolean checkExists() {
        return !this.PASSWORD.equals("");
    }
}
