package login;

public class CheckPassword {
    String enteredPassword;
    String correctPassword;

    public CheckPassword(String enteredPassword, String correctPassword){
        this.enteredPassword = enteredPassword;
        this.correctPassword = correctPassword;
    }

    public boolean passwordsMatch(){
        if (this.correctPassword == this.enteredPassword){
            return true;
        }
        else{
            return false;
        }
    }
}

//entity level
