package login;
import entities.User;

/**
 * The GetUserWithUsername class will have a final attribute USER holds the User from the database with the
 * specified username, or an empty User if no user has this username
 */
public class GetUserWithUsername implements LoginDatabaseGateway{
    /**
     * either the User whose username is username, or an empty User
     */
    private final User USER;

    /**
     * Retrieves from the database the User whose username is username.
     * @param username the username that will be searched for in the database
     */
    public GetUserWithUsername(String username){
        this.USER = getUserWithUsername(username);
    }

    /**
     * Returns the user from the database whose username is user, or an empty user if no user has this username
     * @param username the username that will be searched for in the database
     * @return either the User with username, or an empty user
     */
    @Override
    public User getUserWithUsername(String username) {
        return new LoginDatabaseController(username).getUser();
    }

    public User getUser() {
        return USER;
    }
}
