package login;

import org.junit.jupiter.api.Test;

public class LoginRequestModelUnitTests {
    @Test
    void testLoginRequestModel(){
        LoginRequestModel requestModel =
                new LoginRequestModel("clare", "1235", "1234");
        assert(requestModel.getUsername()== "clare");
        assert(requestModel.getEnteredPassword() == "1235");
        assert(requestModel.getCorrectPassword() == "1234");
    }

    @Test
    void testLoginRequestModelSetMethods(){
        LoginRequestModel requestModel =
                new LoginRequestModel("clare", "1235", "1234");
        requestModel.setUsername("not clare");
        requestModel.setEnteredPassword("9877");
        requestModel.setCorrectPassword("9876");

        assert(requestModel.getUsername() == "not clare");
        assert(requestModel.getEnteredPassword() == "9877");
        assert(requestModel.getCorrectPassword() == "9876");
    }

}
