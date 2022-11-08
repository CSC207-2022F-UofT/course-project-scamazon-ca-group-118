package login;

import features.Cart;
import org.junit.jupiter.api.Test;
import entities.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginInteractorUnitTests {
    static User clare = new User("clare",
            "12345",
            1,
            "clare@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    static User emptyUser = new User("",
            "",
            0,
            "",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());
    @Test
    void testLoginInteractorPasswordsMatch(){
        LoginInteractor interactor = new LoginInteractor("clare", "12345");
        interactor.setUser(clare);
        assert(interactor.getUserExists());
        assert(interactor.getUser() == clare);
        assert(interactor.getUsername().equals("clare"));
        assert(interactor.getEnteredPassword().equals("12345"));
        assert(interactor.shouldLogin());
    }

    @Test
    void testLoginInteractorPasswordWrong(){
        LoginInteractor interactor = new LoginInteractor("clare", "1234");
        interactor.setUser(clare);
        assert(interactor.getUserExists());
        assert(interactor.getUser() == clare);
        assert(interactor.getUsername().equals("clare"));
        assert(interactor.getEnteredPassword().equals("1234"));
        try{
            interactor.shouldLogin();
        }catch(LoginFailed e){
            assert(e.getMessage().equals("The password you entered is incorrect"));
        }
    }

    @Test
    void testLoginInteractorUserDoesNotExist(){
        LoginInteractor interactor = new LoginInteractor("clare", "1234");
        interactor.setUser(emptyUser);
        assertFalse(interactor.getUserExists());
        assert(interactor.getUser() == emptyUser);
        assert(interactor.getUsername().equals("clare"));
        assert(interactor.getEnteredPassword().equals("1234"));
        try{
            interactor.shouldLogin();
        }catch(LoginFailed e){
            assert(e.getMessage().equals("No user exists with this username"));
        }
    }

}
