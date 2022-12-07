package useCase.Register;

import java.io.IOException;

public class RegisterResponseModel {

    private String message;

    public RegisterResponseModel(RegisterRequestModel requestModel) throws IOException {
        String username = requestModel.getUsername();
        String password = requestModel.getPassword();
        String email = requestModel.getUserEmail();
        RegisterInteractor interactor = new RegisterInteractor(password, email, username);
        if (interactor.shouldRegister()){
            interactor.createUser(username, email, password);
            this.message = "Account Created";
        }
    }

    public RegisterResponseModel(String message){
        this.message = message;
    }

    public String getMessage(){
        RegisterPresenter presenter = new RegisterPresenter(this.message);
        return presenter.getMessage();
    }

}
