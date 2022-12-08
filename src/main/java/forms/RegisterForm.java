package forms;

import useCase.Register.RegisterFailed;
import useCase.Register.RegisterRequestModel;
import useCase.Register.RegisterResponseModel;

import java.io.IOException;

public class RegisterForm extends Form{

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private RegisterResponseModel responseModel;

    public RegisterForm(String title, String email, String username, String password, String confirmPassword){
        super(title);
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    protected boolean validateForm() {
        //password and confirm password
        if (!(this.password.equals(this.confirmPassword))) {
            throw new RegisterFailed("Passwords are not the same");
        }else if (password.length() < 8){
            throw new RegisterFailed("Password must be at least 8 characters long");
        }
        //email validation
        if (!this.email.contains("@")) {
            throw new RegisterFailed ("Email must contain '@'");
        }
        return true;
    }

    @Override
    protected void submitForm() throws IOException {
        //run form validate method
        if (this.validateForm()){
            RegisterRequestModel requestModel = new RegisterRequestModel(username, email, password);
            this.responseModel = new RegisterResponseModel(requestModel);
        }
    }

    public RegisterResponseModel getResponseModel() throws IOException {
        this.submitForm();
        return this.responseModel;
    }

}
