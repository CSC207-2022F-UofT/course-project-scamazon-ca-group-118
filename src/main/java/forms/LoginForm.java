package forms;

import login.LoginDatabaseController;
import login.LoginFailed;
import login.LoginRequestModel;
import login.LoginResponseModel;

public class LoginForm extends Form{
    /**
     * The username entered by the user
     */
    public String username;
    /**
     * The password entered by the user
     */
    public String password;

    /**
     * the login response model created from the information entered by the user
     */
    private LoginResponseModel responseModel;

    /**
     * the user associated with username. if no user exists with username, user will be an empty user.
     */
    public User user;


    public LoginForm(String username, String password) {
        super("Log In");
        this.username = username;
        this.password = password;
    }

    public

    @Override
    boolean validateForm() {
        this.user = new LoginDatabaseController(username).getUser();
        LoginRequestModel requestModel = new LoginRequestModel(username, password, user.getPassword());
        this.responseModel = new LoginResponseModel(requestModel);
        return responseModel.getLogIn();
    }

    @Override
    public void submitForm(){
        if(this.validateForm()) {
            LoginPresenter(user, responseModel);
        }
        else{
            throw new LoginFailed(responseModel.getMessage());
        }
        // log in the user

    }
}
