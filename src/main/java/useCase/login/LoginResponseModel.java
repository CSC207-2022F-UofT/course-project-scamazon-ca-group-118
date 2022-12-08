package useCase.login;

import entities.User;


/**
 * The loginResponseModel holds data retrieved from the interactor (in this case, LoginInteractor). Namely, a
 * username, password, id, email, reviews, listings, and cart
 */
public class LoginResponseModel {
    private User user;
    private final LoginInteractor INTERACTOR;

    /**
     * A constructor for the LoginResponseModel
     *
     * @param requestModel the request model to be fed into the LoginInteractor that will generate the
     *                     necessary data for this LoginResponseModel
     */
    public LoginResponseModel(LoginRequestModel requestModel) {
        this.INTERACTOR = new LoginInteractor(requestModel.getUsername(), requestModel.getEnteredPassword());
    }

    public User getUser() {
        if (INTERACTOR.shouldLogin()) {
            this.user = INTERACTOR.getUser();
        }
        return this.user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public LoginInteractor getInteractor(){
        return this.INTERACTOR;
    }
    public void refreshUser(){
        if (INTERACTOR.shouldLogin()) {
            this.user = INTERACTOR.getUser();
        }
    }

}

