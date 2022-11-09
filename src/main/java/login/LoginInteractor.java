package login;
import entities.User;

/**
 * The LoginInteractor class determines whether a user should be logged in, and which User should be logged in.
 */
public class LoginInteractor {
    private String username;
    private final String ENTERED_PASSWORD;
    private boolean userExists;
    private User user;

    /**
     * constructor for the LoginInteractor class with the specified username and enteredPassword, the User user
     * that has the specified username or an empty user, and a boolean value of whether user exists.
     * @param username the username the user entered
     * @param enteredPassword the password the user entered
     */
    public LoginInteractor(String username, String enteredPassword){
        this.username = username;
        this.ENTERED_PASSWORD = enteredPassword;
        this.user = new GetUser().getUserWithUsername(this.username);
        this.userExists = new UserExists(this.user).checkExists();
    }

    /**
     * Returns true iff User may log in. That is, iff user exists, and the password associated
     * with user matches the enteredPassword. Otherwise, throw LoginFailed error.
     * @return true iff User may log in
     */
    public boolean shouldLogin(){
        if(userExists){
            CheckPassword passwordChecker =
                    new CheckPassword(this.ENTERED_PASSWORD, this.user.getPassword());
            if(passwordChecker.passwordsMatch()) {
                return true;
            }
            else{
                throw new LoginFailed("The password you entered is incorrect");
            }
        }
        else throw new LoginFailed("No user exists with this username");
    }

    public void setUserExists(boolean userExists){
        this.userExists = userExists;
    }

    public void setUsername(String username){
        this.username = username;
    }

    /**
     * setter for user, also re-checks if the user exists
     * @param user the User to be set as user
     */
    public void setUser(User user) {
        this.user = user;
        this.setUserExists(new UserExists(this.user).checkExists());
    }

    public boolean getUserExists(){
        return userExists;
    }

    public String getUsername(){
        return username;
    }

    public String getEnteredPassword(){
        return ENTERED_PASSWORD;
    }

    public User getUser() {
        return user;
    }
}
