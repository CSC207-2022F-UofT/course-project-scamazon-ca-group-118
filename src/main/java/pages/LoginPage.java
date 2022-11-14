package pages;

public class LoginPage extends Page{
    private String title;
    public LoginPage() {
        super("Login Page");
    }

    /**TODO:
     * In the action performed section (in view) once we do swing, I'll add a try/catch block that
     * tries:
     * to create a UserPresenter using the controller then open a UserDetailPage with this presenter.
     * catches:
     * the loginFailed error, and will display the error message below the form on the login page.
     */
}
