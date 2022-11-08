package login;

public class LoginInteractor {
    private String username;
    private String enteredPassword;
    private String correctPassword;
    boolean userExists;

    public LoginInteractor(String username, String enteredPassword, String correctPassword){
        this.username = username;
        this.enteredPassword = enteredPassword;
        this.correctPassword = correctPassword;
        this.userExists = new UserExists(this.correctPassword).checkExists();
    }

    public boolean shouldLogin(){
        if(userExists){
            CheckPassword passwordChecker =
                    new CheckPassword(this.enteredPassword, this.correctPassword);
            if(passwordChecker.passwordsMatch()) {
                return true;
            }
        }
        return false;
    }

    public String message(){
        if(!userExists){
            return "No user exists with this username";
        }
        else if(!this.shouldLogin()){
            return "The password you entered is incorrect";
        }
        else{
            return "Success";
        }
    }


    public void setUserExists(boolean userExists){
        this.userExists = userExists;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEnteredPassword(String enteredPassword){
        this.enteredPassword = enteredPassword;
    }

    public void setCorrectPassword(String correctPassword){
        this.correctPassword = correctPassword;
    }

    public boolean getUserExists(){
        return userExists;
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

}
