package forms;
import login.LoginDatabaseController;
import login.LoginFailed;
import login.LoginRequestModel;
import login.LoginResponseModel;
import entities.User;


public class LoginForm extends Form{
    /**
     * The username entered by the user
     */
    public String username;
    /**
     * The password entered by the user
     */
    public String password;

    /**
     * the login response model created from the information entered by the user
     */
    private LoginResponseModel responseModel;

    /**
     * the user associated with username. if no user exists with username, user will be an empty user.
     */
    public User user;


    public LoginForm(String username, String password) {
        super("Log In");
        this.username = username;
        this.password = password;
    }

    @Override
    protected boolean validateForm() {
        if(password.length() > 0 && username.length() > 0) {
            return true;
        }
        else{
            throw new LoginFailed("Please enter a username and password");
        }
    }

    @Override
    protected void submitForm(){
        if(this.validateForm()){
            this.user = new LoginDatabaseController(username).getUser();
            LoginRequestModel requestModel = new LoginRequestModel(username, password);
            this.responseModel = new LoginResponseModel(requestModel);
        }
    }

    /** TODO once we have UserPresenter implemented
     *public UserPresenter getPresenter(){
     *     UserPresenter presenter = new UserPresenter(responseModel.getUsername(),
     *                               responseModel.getID(),
     *                               responseModel.getEmail(),
     *                               responseModel.getReviews(),
     *                               responseModel.getListings(),
     *                               responseModel.getCart());  //or whatever the parameters are for a UserPresenter
     *     return presenter;
     *}
     */

    /**
     * submit the form then return the response model generated
     * @return the response model generated after submitting the form
     */
    public LoginResponseModel getResponseModel() {
        this.submitForm();
        return responseModel;
    }
}
