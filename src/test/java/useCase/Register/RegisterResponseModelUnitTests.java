package useCase.Register;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RegisterResponseModelUnitTests {

    @Test
    void TestLoginWithValidUser() throws IOException {
        RegisterRequestModel requestModel = new RegisterRequestModel("ericguo",
                "ericc.guo@mail.utoronto.ca", "s338687999");
        RegisterResponseModel responseModel = new RegisterResponseModel(requestModel);
        assert responseModel.getMessage().equals("Account Created");
    }

}
