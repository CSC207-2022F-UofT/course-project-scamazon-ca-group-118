package login;

public class LoginResponseModel {
    static boolean logIn;
    public String username;
    static String message;



    public LoginResponseModel(LoginRequestModel requestModel){
        LoginInteractor interactor =
                new LoginInteractor(requestModel.username,
                        requestModel.enteredPassword,
                        requestModel.correctPassword);
        logIn = interactor.shouldLogin();
        this.username = interactor.getUsername();
        message = interactor.message();
    }

    public boolean getLogIn(){
        return logIn;
    }
    public String getMessage() {
        return message;
    }

    public String getUsername(){
        return username;
    }

    public void setLogIn(boolean shouldLogIn){
        logIn = shouldLogIn;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
