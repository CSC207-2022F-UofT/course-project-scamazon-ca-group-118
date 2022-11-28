package forms;

import useCase.Register.RegisterRequestModel;
import useCase.Register.RegisterResponseModel;

public class RegisterForm extends Form{

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private RegisterResponseModel responseModel;

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
        if (!((this.password.equals(this.confirmPassword)) && (password.length() >= 8))) {
            //output error msg
            return false;
        }

        //email validation
        if (!this.email.contains("@")) {
            //error msg
            return false;
        }

        return true;

    }

    @Override
    protected void submitForm() {
        if (this.validateForm()){
            RegisterRequestModel requestModel = new RegisterRequestModel(username, email, password);
            this.responseModel = new RegisterResponseModel(requestModel);
        }
    }

}
