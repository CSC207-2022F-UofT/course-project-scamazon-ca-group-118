package useCase.Register;


public class RegisterPresenter {

    private String message;

    public RegisterPresenter(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
