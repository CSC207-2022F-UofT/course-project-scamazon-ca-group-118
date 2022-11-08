package login;

/**
 * Represents a CheckPassword object with enteredPassword and correctPassowrd attributes. This object will be
 * used to check if the specified passwords match.
 */
public class CheckPassword {
    private final String ENTERED_PASSWORD;
    private final String CORRECT_PASSWORD;

    /**
     * Creates a CheckPassword object with the specified enteredPassword and correctPassword
     * @param enteredPassword the password entered by the user
     * @param correctPassword the correct password for the user being checked
     */
    public CheckPassword(String enteredPassword, String correctPassword){
        this.ENTERED_PASSWORD = enteredPassword;
        this.CORRECT_PASSWORD = correctPassword;
    }

    /**
     * Checks whether ENTERED_PASSWORD and CORRECT_PASSWORD are the same
     * @return ENTERED_PASSWORD == CORRECT_PASSWORD
     */
    public boolean passwordsMatch(){
        return this.CORRECT_PASSWORD.equals(this.ENTERED_PASSWORD);
    }
}

