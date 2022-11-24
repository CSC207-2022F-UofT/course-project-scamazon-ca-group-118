package useCase.Register;

public class RegisterFailed extends RuntimeException{

    public RegisterFailed(String error){ super(error); }

}
