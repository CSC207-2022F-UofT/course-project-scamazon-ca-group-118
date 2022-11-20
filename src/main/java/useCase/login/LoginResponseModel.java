package useCase.login;

import entities.User;

/**
 * The loginResponseModel holds entities.data retrieved from the interactor (in this case, LoginInteractor). Namely, a
 * username, password, id, email, reviews, listings, and cart
 */
public class LoginResponseModel {
    private User user;

    /**
     * A constructor for the LoginResponseModel
     *
     * @param requestModel the request model to be fed into the LoginInteractor that will generate the
     *                     necessary entities.data for this LoginResponseModel
     */
    public LoginResponseModel(LoginRequestModel requestModel) {
        LoginInteractor interactor =
                new LoginInteractor(requestModel.getUsername(), requestModel.getEnteredPassword());
        if (interactor.shouldLogin()) {
            this.user = interactor.getUser();
        }
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

}

