package login;

/**
 * The LoginFailed error is thrown whenever it is found that the password a user entered does not match the
 * correct password for the username they entered, or when no user exists in the database with the username
 * they entered.
 */
public class LoginFailed extends RuntimeException{
    /**
     * Creates a RuntimeException error of type LoginFailed with the specified error message
     * @param error the error message for this error
     */
    public LoginFailed(String error){
        super(error);
    }
}
