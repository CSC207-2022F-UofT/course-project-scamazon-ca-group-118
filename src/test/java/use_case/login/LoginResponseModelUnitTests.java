package use_case.login;

import database.DatabaseController;
import database.GetUser;
import database.RegisterGatewayImplementation;
import entities.Cart;
import entities.Listing;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LoginResponseModelUnitTests {
    private static User user = new User(
            1, "user",
            "12345",
            "user@gmail.com",
            new ArrayList<>(),
            new ArrayList<>(),
            new Cart());

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
        if(Objects.isNull(new GetUser().getUserWithUsername(user.getUsername()))){
            new RegisterGatewayImplementation().createUser(user.getUsername(), user.getEmail(), user.getPassword());
        }
    }

    @AfterAll
    public static void deleteCSVFiles() {
        File usersCSV = new File(db.getUserTablePath());
        usersCSV.delete();
        File listingsCSV = new File(db.getListingTablePath());
        listingsCSV.delete();
    }
    @Test
    void testLoginResponseModelShouldLogin(){
        User loggedIn = new LoginResponseModel(new LoginRequestModel(user.getUsername(), user.getPassword())).getUser();
        assert loggedIn.getUsername().equals(user.getUsername());
        assert loggedIn.getPassword().equals(user.getPassword());
        assert loggedIn.getEmail().endsWith(user.getEmail());
    }
    @Test
    void testLoginResponseModelIncorrectPassword() {
        try {
            new LoginResponseModel(new LoginRequestModel(user.getUsername(), "incorretPassword"))
                    .getUser();
            assert false;
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("The password you entered is incorrect"));
        }
    }
    @Test
    void testLoginResponseModelNoUserWithUsername() {
        try {
            new LoginResponseModel(new LoginRequestModel("IncorrectUsername", user.getPassword())).getUser();
            assert false;
        } catch (LoginFailed e) {
            assert (e.getMessage().equals("No user exists with this username"));
        }
    }
}
