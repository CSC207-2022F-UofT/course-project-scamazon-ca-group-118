package forms;

import useCase.Register.RegisterRequestModel;
import useCase.Register.RegisterResponseModel;

public class RegisterForm extends Form{

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private RegisterResponseModel responseModel;
    private String message;
    public RegisterForm(String title) {
        super(title);
        this.username = "";
        this.email = "";
        this.password = "";
        this.confirmPassword = "";
    }

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
            this.message = "Passwords are not the same!";
            return false;
        }else if (password.length() < 8){
            this.message = "Password must be at least 8 characters long!";
            return false;
        }

        //email validation
        if (!this.email.contains("@")) {
            this.message = "Email must contain '@'";
            return false;
        }

        return true;
    }

    @Override
    protected void submitForm() {
        if (this.validateForm()){
            RegisterRequestModel requestModel = new RegisterRequestModel(username, email, password);
            this.responseModel = new RegisterResponseModel(requestModel);
        }else{
            this.responseModel = new RegisterResponseModel(this.message);
        }
    }

    public RegisterResponseModel getResponseModel(){
        this.submitForm();
        return this.responseModel;
    }

}
