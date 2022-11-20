package forms;
import useCase.login.LoginRequestModel;
import useCase.login.LoginResponseModel;


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
     * the useCase.login response model created from the information entered by the user
     */
    private LoginResponseModel responseModel;



    public LoginForm(String username, String password) {
        super("Log In");
        this.username = username;
        this.password = password;
    }

    @Override
    protected boolean validateForm() {
        return password.length() > 0 && username.length() > 0;
    }

    @Override
    protected void submitForm(){
        if(this.validateForm()){
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
