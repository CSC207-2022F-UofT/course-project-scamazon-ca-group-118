package use_case.register;


import org.junit.jupiter.api.Test;

public class RegisterRequestModelUnitTests {

    @Test
    void TestRegisterRequestModel(){
        RegisterRequestModel registerRequestModel = new RegisterRequestModel("eric1230",
                "eric.guo1230@gmail.com", "12345678");
        assert registerRequestModel.getUsername().equals("eric1230");
        assert registerRequestModel.getUserEmail().equals("eric.guo1230@gmail.com");
        assert registerRequestModel.getPassword().equals("12345678");
    }

    @Test
    void TestRegisterRequestSetMethods(){
        RegisterRequestModel registerRequestModel = new RegisterRequestModel("", "", "");
        registerRequestModel.setEmail("eric.guo1230@gmail.com");
        registerRequestModel.setUsername("ericguo1230");
        registerRequestModel.setPassword("12345678");
        assert registerRequestModel.getUsername().equals("ericguo1230");
        assert registerRequestModel.getUserEmail().equals("eric.guo1230@gmail.com");
        assert registerRequestModel.getPassword().equals("12345678");
    }
}
