package all.useCase.login;

import org.junit.jupiter.api.Test;

public class LoginRequestModelUnitTests {
    @Test
    void testLoginRequestModel() {
        LoginRequestModel requestModel =
                new LoginRequestModel("clare", "1235");
        assert (requestModel.getUsername().equals("clare"));
        assert (requestModel.getEnteredPassword().equals("1235"));
    }

    @Test
    void testLoginRequestModelSetMethods() {
        LoginRequestModel requestModel =
                new LoginRequestModel("clare", "1235");
        requestModel.setUsername("not clare");
        requestModel.setEnteredPassword("9877");

        assert (requestModel.getUsername().equals("not clare"));
        assert (requestModel.getEnteredPassword().equals("9877"));
    }

}
