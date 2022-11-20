package all.useCase.login;


/**
 * TODO uncomment and implement these tests once Database implemented
public class LoginResponseModelUnitTests {
    @Test
    void testLoginResponseModelShouldLogin() {
        LoginResponseModel responseModel = new LoginResponseModel(new LoginRequestModel("clare",
                "12345"));
        assert (responseModel.getUser().getUsername().equals("clare"));
        assert (responseModel.getUser().getPassword().equals("12345"));
        assert (responseModel.getUser().getID() == 1);
        assert (responseModel.getUser().getEmail().equals("clare@gmail.com"));
        assert (responseModel.getUser().getReviews().isEmpty());
        assert (responseModel.getUser().getListings().isEmpty());
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
*/