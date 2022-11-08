package pages;

public class LoginPage extends Page{
    private String title;
    public LoginPage() {
        super("Login Page");
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
