package useCase.Register;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RegisterInteractorUnitTests {

    @Test
    void TestShouldRegisterWithNoUsername() throws IOException {
        RegisterInteractor registerInteractor = new RegisterInteractor("s338687999", "eric@gmail.com",
                "");
        try {
            registerInteractor.shouldRegister();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Please enter a username"));
        }
    }

    @Test
    void TestShouldRegisterWithTakenEmail() throws IOException {
        RegisterInteractor registerInteractor = new RegisterInteractor("s338687999", "eric@gmail.com",
                "ericguo");
        registerInteractor.createUser(registerInteractor.getUsername(), registerInteractor.getEmail(),
                registerInteractor.getPassword());
        RegisterInteractor registerInteractor2 = new RegisterInteractor("s338687999", "eric@gmail.com",
                "Allen123");
        assert (registerInteractor2.getEmailExists() == true);
        try {
            registerInteractor2.shouldRegister();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Email is already taken"));
        }
    }

    @Test
    void TestShouldRegisterWithTakenUsername() throws IOException {
        RegisterInteractor registerInteractor = new RegisterInteractor("s338687999", "eric@gmail.com",
                "ericguo");
        registerInteractor.createUser(registerInteractor.getUsername(), registerInteractor.getEmail(),
                registerInteractor.getPassword());
        RegisterInteractor registerInteractor2 = new RegisterInteractor("s338687999", "ericguo@gmail.com",
                "ericguo");
        assert (registerInteractor2.getUsernameExists() == true);
        try {
            registerInteractor2.shouldRegister();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Username is already taken"));
        }
    }

    @Test
    void TestShouldRegisterWithTakenUsernameAndEmail() throws IOException {
        RegisterInteractor registerInteractor = new RegisterInteractor("s338687999", "eric@gmail.com",
                "ericguo");
        registerInteractor.createUser(registerInteractor.getUsername(), registerInteractor.getEmail(),
                registerInteractor.getPassword());
        RegisterInteractor registerInteractor2 = new RegisterInteractor("s338687999", "eric@gmail.com",
                "ericguo");
        assert (registerInteractor2.getUsernameExists() == true);
        assert (registerInteractor2.getEmailExists() == true);
        try {
            registerInteractor2.shouldRegister();
        } catch (RegisterFailed e) {
            assert (e.getMessage().equals("Username and Email are already taken"));
        }
    }
}
