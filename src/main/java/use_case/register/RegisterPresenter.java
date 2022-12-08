package use_case.register;


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
