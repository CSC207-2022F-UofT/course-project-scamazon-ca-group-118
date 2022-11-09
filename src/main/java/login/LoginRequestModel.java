package login;

/**
 * The LoginRequestModel holds data that will be sent from the constructor (in this case, the LoginForm) to the
 * interactor (in this case, LoginInteractor)
 */
public class LoginRequestModel {
    private String username;
    private String enteredPassword;

    /**
     * The constructor for the LoginRequestModel with the specified username and enteredPassword
     *
     * @param username        the username that the user entered and will be stored in this object
     * @param enteredPassword the password that the user entered and will be stored in this object
     */
    public LoginRequestModel(String username, String enteredPassword) {
        this.username = username;
        this.enteredPassword = enteredPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

}
