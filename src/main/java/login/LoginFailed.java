package login;

public class LoginFailed extends RuntimeException{
    public LoginFailed(String error){
        super(error);
    }
}
