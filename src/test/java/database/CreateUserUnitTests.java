package database;

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

public class CreateUserUnitTests {
    public static final DatabaseController db = new DatabaseController();

    @BeforeAll
    public static void setUp() throws IOException {
        db.setListingTablePath("src/test/java/database/Listings.csv");
        db.setUserTablePath("src/test/java/database/Users.csv");
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
    public void testCreateOneUser() throws IOException {
        db.createUser("user", "pass", "test@test.com");
        User expectedUser = new User(0, "user", "pass", "test@test.com", new ArrayList<>(),
                new ArrayList<>(), new Cart());
        User actualUser = db.getUserWithUsername("user");
        assert actualUser.equals(expectedUser);
    }

    @Test
    public void testCreateMultipleUser() throws IOException {
        db.createUser("user1", "pass1", "test1@test.com");
        db.createUser("user2", "pass2", "test2@test.com");
        db.createUser("user3", "pass3", "test3@test.com");
        User expectedUser1 = new User(0, "user1", "pass1", "test1@test.com", new ArrayList<>(),
                new ArrayList<>(), new Cart());
        User expectedUser2 = new User(1, "user2", "pass2", "test2@test.com", new ArrayList<>(),
                new ArrayList<>(), new Cart());
        User expectedUser3 = new User(2, "user3", "pass3", "test3@test.com", new ArrayList<>(),
                new ArrayList<>(), new Cart());
        User actualUser1 = db.getUserWithUsername("user1");
        User actualUser2 = db.getUserWithUsername("user2");
        User actualUser3 = db.getUserWithUsername("user3");
        assert expectedUser1.equals(actualUser1);
        assert expectedUser2.equals(actualUser2);
        assert expectedUser3.equals(actualUser3);
    }
}
