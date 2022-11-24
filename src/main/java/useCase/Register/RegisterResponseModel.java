package useCase.Register;

import entities.User;

public class RegisterResponseModel {

    private User user;

    public RegisterResponseModel(RegisterRequestModel requestModel){
        String username = requestModel.getUsername();
        String password = requestModel.getPassword();
        String email = requestModel.getUserEmail();
        RegisterInteractor interactor = new RegisterInteractor(password, username, email);
        if (interactor.shouldRegister(username, email)){
            this.user = interactor.createUser(username, email, password);
        }
    }

}
