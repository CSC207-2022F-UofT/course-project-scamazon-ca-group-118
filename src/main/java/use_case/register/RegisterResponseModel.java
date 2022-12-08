package use_case.register;

import java.io.IOException;

public class RegisterResponseModel {

    private String message;

    public RegisterResponseModel(RegisterRequestModel requestModel) throws IOException {
        String username = requestModel.getUsername();
        String password = requestModel.getPassword();
        String email = requestModel.getUserEmail();
        RegisterInteractor interactor = new RegisterInteractor(password, email, username);
        if (interactor.shouldRegister()){
            interactor.createUser();
            this.message = "Account Created";
        }
    }

    public String getMessage(){
        RegisterPresenter presenter = new RegisterPresenter(this.message);
        return presenter.getMessage();
    }

}
