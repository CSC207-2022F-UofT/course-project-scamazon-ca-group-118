package useCase.login;


import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LoginResponseModelUnitTests {
    static User clare = new User(1, "clare",
            "12345",
            "clare@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    @Test
    void testLoginResponseModelShouldLogin() {
        LoginResponseModel responseModel = new LoginResponseModel(new LoginRequestModel("clare",
                "12345"));
        responseModel.getInteractor().setUser(clare);
        responseModel.getInteractor().setUserExists(true);
        responseModel.refreshUser();
        assert (responseModel.getUser().getUsername().equals("clare"));
        assert (responseModel.getUser().getPassword().equals("12345"));
        assert (responseModel.getUser().getID() == 1);
        assert (responseModel.getUser().getEmail().equals("clare@gmail.com"));
        assert (responseModel.getUser().getReviews().isEmpty());
        assert (responseModel.getUser().getListings().isEmpty());
        assert(responseModel.getUser().getCart().countItems() == 0);
    }

    @Test
    void testLoginResponseModelPasswordsWrong() {
        try {
            LoginResponseModel responseModel =
                    new LoginResponseModel(new LoginRequestModel("clare", "1234"));
            responseModel.getInteractor().setUser(clare);
            responseModel.refreshUser();
            responseModel.getUser();
            assert false;
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("The password you entered is incorrect"));
        }
    }

    @Test
    void testLoginResponseModelNoUserWithUsername() {
        try {
            LoginResponseModel responseModel =
                    new LoginResponseModel(new LoginRequestModel("not clare", "1234"));
            responseModel.getInteractor().setUser(null);
            responseModel.refreshUser();
            responseModel.getUser();
            assert false;
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("No user exists with this username"));
        }
    }
}