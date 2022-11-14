package forms;

public class RegisterForm extends Form {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterForm(String title) {
        super(title);
    }

    @Override
    protected boolean validateForm() {
        //password and confirm password
        if (!((this.password.equals(this.confirmPassword)) && (password.length() >= 8))) {
            //output error msg
            return false;
        }

        //email validation
        if (!this.email.contains("@")) {
            //error msg
            return false;
        }

        //use database interactor to see if input is unique

        return true;

    }

    @Override
    protected void submitForm() {

    }

    public void createUser(String username, String email, String password) {
        //TODO: call createUser method in user class using database interactor
    }

}
