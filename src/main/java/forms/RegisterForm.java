package forms;

public class RegisterForm extends Form{

    private String username;
    private String email;
    private String password;
    private String confirmpassword;

    public RegisterForm(String title) {
        super(title);
    }

    @Override
    boolean validateForm() {
        return false;
    }

    @Override
    void submitForm() {

    }
    public void createUser(String username, String email, String password){
        //call createUser method in user class using database interactor
    }

}
