package useCase.Register;

import forms.RegisterForm;

public class RegisterPresenter {

    private String message;

    public RegisterPresenter(RegisterResponseModel response){
        this.message = response.getMessage();
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
