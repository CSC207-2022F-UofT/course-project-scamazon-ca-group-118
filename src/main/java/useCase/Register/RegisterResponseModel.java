package useCase.Register;

import entities.User;

public class RegisterResponseModel {

    private User user;
    private String message;

    public RegisterResponseModel(RegisterRequestModel requestModel){
        String username = requestModel.getUsername();
        String password = requestModel.getPassword();
        String email = requestModel.getUserEmail();
        RegisterInteractor interactor = new RegisterInteractor(password, username, email);
        if (interactor.shouldRegister(username, email)){
            this.user = interactor.createUser(username, email, password);
            this.message = "Account Created";
        }else{
            this.message = "";
        }
    }

    public RegisterResponseModel(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
