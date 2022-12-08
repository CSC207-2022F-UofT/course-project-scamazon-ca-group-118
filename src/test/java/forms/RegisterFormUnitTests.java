package forms;

import org.junit.jupiter.api.Test;
import useCase.Register.RegisterFailed;
import useCase.Register.RegisterResponseModel;

import java.io.IOException;

public class RegisterFormUnitTests {

    @Test
    void TestRegisterValidateFormPasswordLengthLessThan8() {
        RegisterForm registerForm = new RegisterForm("Register", "eric.guo1230@gmail.com",
                "eric.guo", "1234567", "1234567");
        try {
            registerForm.validateForm();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Password must be at least 8 characters long"));
        }
    }

    @Test
    void TestRegisterValidateFormInvalidEmail() {
        RegisterForm registerForm = new RegisterForm("Register", "eric.guo1230gmail.com",
                "eric.guo", "12345678", "12345678");
        try {
            registerForm.validateForm();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Email must contain '@'"));
        }
    }

    @Test
    void TestRegisterValidateFormInvalidConfirmPassword() {
        RegisterForm registerForm = new RegisterForm("Register", "eric.guo1230gmail.com",
                "eric.guo", "12345678", "1234567");
        try {
            registerForm.validateForm();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Passwords are not the same"));
        }
    }

    @Test
    void TestRegisterValidateFormValidUser() {
        RegisterForm registerForm = new RegisterForm("Register", "eric.guo1230@gmail.com",
                "eric.guo", "12345678", "12345678");
        assert(registerForm.validateForm() == true);
    }

    @Test
    void TestRegisterSubmitForm() throws IOException {
        RegisterForm registerForm = new RegisterForm("Register", "eriqq.guo1230@gmail.com",
                "eriqq", "12345678", "12345678");
        RegisterResponseModel responseModel = registerForm.getResponseModel();
        assert (responseModel.getEmail().equals("eriqq.guo1230@gmail.com"));
        assert (responseModel.getUsername().equals("eriqq"));
        assert (responseModel.getPassword().equals("12345678"));
    }

}
