package all.forms;

import all.useCase.login.LoginFailed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginFormUnitTests {
    @Test
    void LoginFormInvalidUsername() {
        LoginForm form = new LoginForm("", "12345");
        assertFalse(form.validateForm());
        form.submitForm();
        assertNull(form.getResponseModel());
    }

    @Test
    void LoginFormInvalidPassword() {
        LoginForm form = new LoginForm("clare", "");
        assertFalse(form.validateForm());
        form.submitForm();
        assertNull(form.getResponseModel());
    }

    @Test
    void LoginFormValidEntryUserDoesNotExist() {
        LoginForm form = new LoginForm("not clare", "12345");
        try {
            form.submitForm();
            assert 4 == 5; //assert a false statement to ensure the try failed
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("No user exists with this username"));
        }
    }

    @Test
    void LoginFormValidEntryWrongPassword() {
        LoginForm form = new LoginForm("clare", "1234");
        assert (form.validateForm());
        try {
            form.submitForm();
            assert 4 == 5; //assert a false statement to ensure the try failed
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("The password you entered is incorrect"));
        }
    }

    @Test
    void LoginFormValidEntryCorrectPassword() {
        LoginForm form = new LoginForm("clare", "12345");
        assert (form.getResponseModel().getUser().getUsername().equals("clare"));
        assert (form.getResponseModel().getUser().getPassword().equals("12345"));
        assert (form.getResponseModel().getUser().getID() == 1);
        assert (form.getResponseModel().getUser().getEmail().equals("clare@gmail.com"));
        assert (form.getResponseModel().getUser().getReviews().isEmpty());
        assert (form.getResponseModel().getUser().getListings().isEmpty());
    }
}
