package useCase.Register;

import java.io.IOException;

public class RegisterResponseModel {

    private String message;
    private String username;
    private String password;
    private String email;

    public RegisterResponseModel(RegisterRequestModel requestModel) throws IOException {
        this.username = requestModel.getUsername();
        this.password = requestModel.getPassword();
        this.email = requestModel.getUserEmail();
        RegisterInteractor interactor = new RegisterInteractor(password, email, username);
        if (interactor.shouldRegister()){
            interactor.createUser(username, email, password);
            this.message = "Account Created";
        }
    }
    
    public String getMessage(){
        RegisterPresenter presenter = new RegisterPresenter(this.message);
        return presenter.getMessage();
    }
}
