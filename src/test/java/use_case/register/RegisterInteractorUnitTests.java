package use_case.register;

import database.DatabaseController;
import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class RegisterInteractorUnitTests {

    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() throws IOException {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.createNewFile();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.createNewFile();
    }
    @BeforeEach
    public void resetCSVFiles() throws IOException {
        File usersCSV = new File(db.getUserTablePath());
        if (usersCSV.delete()) {
            usersCSV.createNewFile();
        }
        File listingsCSV = new File(db.getListingTablePath());
        if (listingsCSV.delete()) {
            listingsCSV.createNewFile();
        }
        Listing.setNextId(0);
        User.setNextId(0);
    }

    @AfterAll
    public static void deleteCSVFiles() {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.delete();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.delete();
    }

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
        registerInteractor.createUser();
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
        registerInteractor.createUser();
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
        registerInteractor.createUser();
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
