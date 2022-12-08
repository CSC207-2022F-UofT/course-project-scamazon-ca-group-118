package use_case.register;

public class RegisterFailed extends RuntimeException{

    public RegisterFailed(String error){ super(error); }

}
