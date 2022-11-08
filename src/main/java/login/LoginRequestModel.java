package login;

public class LoginRequestModel {
    /**
     * the username entered by the user
     */
    String username;

    /**
     * the password entered by the user
     */
    String enteredPassword;

    /**
     * the correct password for the user with username
     */
    String correctPassword;

    public LoginRequestModel(String username, String enteredPassword, String correctPassword){
        this.username = username;
        this.enteredPassword = enteredPassword;
        this.correctPassword = correctPassword;
    }

    public String getUsername(){
        return username;
    }

    public String getEnteredPassword(){
        return enteredPassword;
    }

    public String getCorrectPassword(){
        return correctPassword;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setCorrectPassword(String correctPassword){
        this.correctPassword = correctPassword;
    }

    public void setEnteredPassword(String enteredPassword){
        this.enteredPassword = enteredPassword;
    }

}
