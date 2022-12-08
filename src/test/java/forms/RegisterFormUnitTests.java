package forms;

import database.DatabaseController;
import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterFailed;
import use_case.register.RegisterResponseModel;

import java.io.File;
import java.io.IOException;

public class RegisterFormUnitTests {

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
        assert (registerForm.validateForm() == true);
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
