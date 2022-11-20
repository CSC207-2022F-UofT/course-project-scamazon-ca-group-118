package useCase.login;

import org.junit.jupiter.api.Test;


public class LoginResponseModelUnitTests {
    @Test
    void testLoginResponseModelShouldLogin() {
        LoginResponseModel responseModel = new LoginResponseModel(new LoginRequestModel("clare",
                "12345"));
//        assert (responseModel.getUsername().equals("clare"));
//        assert (responseModel.getPassword().equals("12345"));
//        assert (responseModel.getId() == 1);
//        assert (responseModel.getEmail().equals("clare@gmail.com"));
//        assert (responseModel.getReviews().isEmpty());
//        assert (responseModel.getListings().isEmpty());
        //assert(responseModel.getCart().isEmpty());
    }

    @Test
    void testLoginResponseModelPasswordsWrong() {
        try {
            new LoginResponseModel(new LoginRequestModel("clare", "1234"));
            assert 4 == 5; //assert a false statement to ensure the try failed
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("The password you entered is incorrect"));
        }
    }

    @Test
    void testLoginResponseModelNoUserWithUsername() {
        try {
            new LoginResponseModel(new LoginRequestModel("not clare", "1234"));
            assert 4 == 5; //assert a false statement to ensure the try failed
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("No user exists with this username"));
        }
    }
}
