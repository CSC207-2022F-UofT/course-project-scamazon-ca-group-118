package forms;

import useCase.Register.RegisterFailed;
import useCase.Register.RegisterRequestModel;
import useCase.Register.RegisterResponseModel;

import java.io.IOException;

public class RegisterForm extends Form{

    private final String USERNAME;
    private final String EMAIL;
    private final String PASSWORD;
    private final String CONFIRM_PASSWORD;
    private RegisterResponseModel responseModel;

    public RegisterForm(String title, String email, String username, String password, String confirmPassword){
        super(title);
        this.USERNAME = username;
        this.EMAIL = email;
        this.PASSWORD = password;
        this.CONFIRM_PASSWORD = confirmPassword;
    }

    @Override
    protected boolean validateForm() {
        //password and confirm password
        if (!(this.PASSWORD.equals(this.CONFIRM_PASSWORD))) {
            throw new RegisterFailed("Passwords are not the same");
        }else if (PASSWORD.length() < 8){
            throw new RegisterFailed("Password must be at least 8 characters long");
        }
        //email validation
        if (!this.EMAIL.contains("@")) {
            throw new RegisterFailed ("Email must contain '@'");
        }
        return true;
    }

    @Override
    protected void submitForm() throws IOException {
        //run form validate method
        if (this.validateForm()) {
            RegisterRequestModel requestModel = new RegisterRequestModel(USERNAME, EMAIL, PASSWORD);
            this.responseModel = new RegisterResponseModel(requestModel);
        }
    }

    public RegisterResponseModel getResponseModel() throws IOException {
        this.submitForm();
        return this.responseModel;
    }

}
