package use_case.login;


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
        User user = responseModel.getUser();
        assert (user.getUsername().equals("clare"));
        assert (user.getPassword().equals("12345"));
        assert (user.getID() == 1);
        assert (user.getEmail().equals("clare@gmail.com"));
        assert (user.getREVIEWS().isEmpty());
        assert (user.getListings().isEmpty());
        assert(user.getCart().countItems() == 0);
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