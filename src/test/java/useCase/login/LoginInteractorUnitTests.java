package useCase.login;

import entities.Cart;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginInteractorUnitTests {
    static User clare = new User(1, "clare",
            "12345",
            "clare@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    @Test
    void testLoginInteractorPasswordsMatch() {
        LoginInteractor interactor = new LoginInteractor("clare", "12345");
        interactor.setUser(clare);
        interactor.setUserExists(true);
        assert (interactor.getUserExists());
        assert (interactor.getUser() == clare);
        assert (interactor.getUsername().equals("clare"));
        assert (interactor.getENTERED_PASSWORD().equals("12345"));
        assert (interactor.shouldLogin());
    }

    @Test
    void testLoginInteractorPasswordWrong() {
        LoginInteractor interactor = new LoginInteractor("clare", "1234");
        interactor.setUser(clare);
        interactor.setUserExists(true);
        assert (interactor.getUserExists());
        assert (interactor.getUser() == clare);
        assert (interactor.getUsername().equals("clare"));
        assert (interactor.getENTERED_PASSWORD().equals("1234"));
        try {
            interactor.shouldLogin();
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("The password you entered is incorrect"));
        }
    }

    @Test
    void testLoginInteractorUserDoesNotExist() {
        LoginInteractor interactor = new LoginInteractor("clare", "1234");
        interactor.setUser(null);
        assertFalse(interactor.getUserExists());
        assert (interactor.getUsername().equals("clare"));
        assert (interactor.getENTERED_PASSWORD().equals("1234"));
        try {
            interactor.shouldLogin();
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("No user exists with this username"));
        }
    }

}
